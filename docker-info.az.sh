LOCATION="Central US"
GROUP="devtest1"
CNAME="msfintest2"
IMAGE="msdevreg1.azurecr.io/msdev/finance-api:v1"

az group show --name $GROUP

az container show \
--resource-group $GROUP \
--name $CNAME \
--query "{FQDN:ipAddress.fqdn,ProvisioningState:provisioningState}" \
--out table

# get password for registry
# az acr credential show --name $GROUP