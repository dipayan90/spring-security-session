set -x
export DEFAULTIMG="dipayan90/sessionwebapp"
export IMAGE=${IMAGE:-$DEFAULTIMG}
export FLEETCTL_TUNNEL="172.17.8.101"
export FLEETCTL_SSH_USERNAME=core;

if [ -z ${FLEETCTL_TUNNEL} ]; then
    echo "FLEETCTL_TUNNEL must be defined, exiting"
    exit 1
fi

ssh-add ~/.ssh/fleetctl
FLEETCTL_OPTIONS=''

##### First, shut down existing units

regex="sessionwebapp@(.*)\.service"
running_units="$(fleetctl $FLEETCTL_OPTIONS list-units | cut -f 1 | uniq)"
echo "Stopping any running agents"
for i in $running_units; do
    if [[ "$i" =~ $regex ]]; then
        unit="${BASH_REMATCH[1]}"
        	echo "Stopping sessionwebapp@${unit}"
            fleetctl $FLEETCTL_OPTIONS stop sessionwebapp@${unit}.service
			echo "Waiting 15 seconds..."
            sleep 15;
            echo "..and destroying"
            fleetctl $FLEETCTL_OPTIONS destroy sessionwebapp@${unit}.service
    fi
done
echo "Starting agents"
fleetctl $FLEETCTL_OPTIONS start sessionwebapp@1.service
echo "Waiting for sessionwebapp to be running"
FULLID="sessionwebapp@$1.service"
i=0
while [ 1 ]; do
  if [ $i -ge 15 ]; then
    echo "Failed after 15 attempts waiting for sessionwebapp" >&2
    exit 1
  fi
  STATUS=$(fleetctl $FLEETCTL_OPTIONS list-units | grep $FULLID | awk '{print $4}' | uniq)
  if [ "$STATUS" == "running" ]; then
    break
  elif [ "$STATUS" == "failed" -o "$STATUS" == "dead" ]; then
    echo "Caught failure while waiting to see if deployment succeeded. See the system logs for details." >&2
    exit 1
  fi
  i=$((i + 1))
  sleep 30
done