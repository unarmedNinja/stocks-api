az acr login

az acr run --registry msdevreg1 \
  --cmd '$Registry/msdev/finance-api:v1' /dev/null