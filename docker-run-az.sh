LOCATION="centralus"
GROUP="devtest1"
CNAME="msfintest2"
IMAGE="msdevreg1.azurecr.io/msdev/finance-api:v1"
REGUSER="msdevreg1"

#get password from info script
REGPASS=""

#PLANNAME="appserviceplan1"
PLANNAME="ASP-DefaultWebEastUS-aa64"
SKU="F1"

#
az acr login


#az container create \
#--resource-group $GROUP \
#--name $CNAME \
#--image $IMAGE \
#--dns-name-label $CNAME \
#--ports 80 \
#--os-type Linux \
#--location $LOCATION \
#--registry-username $REGUSER \
#--registry-password $REGPASS

## create a service plan
#az appservice plan create \
#--name $PLANNAME \
#--resource-group $GROUP \
#--is-linux \
#--location $LOCATION \
#--sku $SKU \
#--number-of-workers 1


# create the web app
az webapp create -n $CNAME -g $GROUP -p $PLANNAME -i $IMAGE --debug

#enable logging
az webapp log config -n $CNAME -g $GROUP --web-server-logging filesystem

#az webapp restart --name $CNAME --resource-group $GROUP