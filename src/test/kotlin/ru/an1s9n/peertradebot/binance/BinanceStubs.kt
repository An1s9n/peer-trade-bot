package ru.an1s9n.peertradebot.binance

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.matching.EqualToPattern
import com.github.tomakehurst.wiremock.matching.MatchesJsonPathPattern
import com.marcinziolo.kotlin.wiremock.equalTo
import com.marcinziolo.kotlin.wiremock.post
import com.marcinziolo.kotlin.wiremock.returnsJson

fun WireMockServer.searchPage1SuitablePriceStub() {
  post {
    urlPath equalTo "/bapi/c2c/v2/friendly/c2c/adv/search"
    withBuilder { withRequestBody(MatchesJsonPathPattern("\$.page", EqualToPattern("1"))) }
  } returnsJson {
    body =
      // language=JSON
      """
        {
          "code": "000000",
          "message": null,
          "messageDetail": null,
          "data": [
            {
              "adv": {
                "advNo": "12557690784292093952",
                "classify": "2",
                "tradeType": "SELL",
                "asset": "USDT",
                "fiatUnit": "USD",
                "advStatus": null,
                "priceType": null,
                "priceFloatingRatio": null,
                "rateFloatingRatio": null,
                "currencyRate": null,
                "price": "1.014",
                "initAmount": null,
                "surplusAmount": "13530.63",
                "amountAfterEditing": null,
                "maxSingleTransAmount": "20000.00",
                "minSingleTransAmount": "500.00",
                "buyerKycLimit": null,
                "buyerRegDaysLimit": null,
                "buyerBtcPositionLimit": null,
                "remarks": null,
                "autoReplyMsg": "",
                "payTimeLimit": 15,
                "tradeMethods": [
                  {
                    "payId": null,
                    "payMethodId": "",
                    "payType": null,
                    "payAccount": null,
                    "payBank": null,
                    "paySubBank": null,
                    "identifier": "BankofGeorgia",
                    "iconUrlColor": null,
                    "tradeMethodName": "Bank of Georgia",
                    "tradeMethodShortName": null,
                    "tradeMethodBgColor": "#F26724"
                  }
                ],
                "userTradeCountFilterTime": null,
                "userBuyTradeCountMin": null,
                "userBuyTradeCountMax": null,
                "userSellTradeCountMin": null,
                "userSellTradeCountMax": null,
                "userAllTradeCountMin": null,
                "userAllTradeCountMax": null,
                "userTradeCompleteRateFilterTime": null,
                "userTradeCompleteCountMin": null,
                "userTradeCompleteRateMin": null,
                "userTradeVolumeFilterTime": null,
                "userTradeType": null,
                "userTradeVolumeMin": null,
                "userTradeVolumeMax": null,
                "userTradeVolumeAsset": null,
                "createTime": null,
                "advUpdateTime": null,
                "fiatVo": null,
                "assetVo": null,
                "advVisibleRet": null,
                "takerAdditionalKycRequired": 0,
                "assetLogo": null,
                "assetScale": 2,
                "fiatScale": 2,
                "priceScale": 3,
                "fiatSymbol": "${'$'}",
                "isTradable": true,
                "dynamicMaxSingleTransAmount": "13681.74",
                "minSingleTransQuantity": "493.09",
                "maxSingleTransQuantity": "19723.86",
                "dynamicMaxSingleTransQuantity": "13492.85",
                "tradableQuantity": "13492.85",
                "commissionRate": "0.00280000",
                "takerCommissionRate": null,
                "tradeMethodCommissionRates": [],
                "launchCountry": null,
                "abnormalStatusList": null,
                "closeReason": null,
                "storeInformation": null,
                "allowTradeMerchant": null
              },
              "advertiser": {
                "userNo": "s4a9e298d0d7c327dbfcc5c0b32d95895",
                "realName": null,
                "nickName": "Zura",
                "margin": null,
                "marginUnit": null,
                "orderCount": null,
                "monthOrderCount": 44,
                "monthFinishRate": 1.000,
                "positiveRate": 0.99978880,
                "advConfirmTime": null,
                "email": null,
                "registrationTime": null,
                "mobile": null,
                "userType": "merchant",
                "tagIconUrls": [],
                "userGrade": 3,
                "userIdentity": "MASS_MERCHANT",
                "proMerchant": null,
                "badges": [
                  "Ordinary"
                ],
                "isBlocked": false,
                "activeTimeInSecond": 237
              }
            },
            {
              "adv": {
                "advNo": "11555491607579504640",
                "classify": "2",
                "tradeType": "SELL",
                "asset": "USDT",
                "fiatUnit": "USD",
                "advStatus": null,
                "priceType": null,
                "priceFloatingRatio": null,
                "rateFloatingRatio": null,
                "currencyRate": null,
                "price": "1.014",
                "initAmount": null,
                "surplusAmount": "4373.19",
                "amountAfterEditing": null,
                "maxSingleTransAmount": "15000.00",
                "minSingleTransAmount": "150.00",
                "buyerKycLimit": null,
                "buyerRegDaysLimit": null,
                "buyerBtcPositionLimit": null,
                "remarks": null,
                "autoReplyMsg": "",
                "payTimeLimit": 15,
                "tradeMethods": [
                  {
                    "payId": null,
                    "payMethodId": "",
                    "payType": null,
                    "payAccount": null,
                    "payBank": null,
                    "paySubBank": null,
                    "identifier": "BankofGeorgia",
                    "iconUrlColor": null,
                    "tradeMethodName": "Bank of Georgia",
                    "tradeMethodShortName": null,
                    "tradeMethodBgColor": "#F26724"
                  }
                ],
                "userTradeCountFilterTime": null,
                "userBuyTradeCountMin": null,
                "userBuyTradeCountMax": null,
                "userSellTradeCountMin": null,
                "userSellTradeCountMax": null,
                "userAllTradeCountMin": null,
                "userAllTradeCountMax": null,
                "userTradeCompleteRateFilterTime": null,
                "userTradeCompleteCountMin": null,
                "userTradeCompleteRateMin": null,
                "userTradeVolumeFilterTime": null,
                "userTradeType": null,
                "userTradeVolumeMin": null,
                "userTradeVolumeMax": null,
                "userTradeVolumeAsset": null,
                "createTime": null,
                "advUpdateTime": null,
                "fiatVo": null,
                "assetVo": null,
                "advVisibleRet": null,
                "takerAdditionalKycRequired": 0,
                "assetLogo": null,
                "assetScale": 2,
                "fiatScale": 2,
                "priceScale": 3,
                "fiatSymbol": "${'$'}",
                "isTradable": true,
                "dynamicMaxSingleTransAmount": "4426.96",
                "minSingleTransQuantity": "147.92",
                "maxSingleTransQuantity": "14792.89",
                "dynamicMaxSingleTransQuantity": "4365.55",
                "tradableQuantity": "4365.55",
                "commissionRate": "0.00175000",
                "takerCommissionRate": null,
                "tradeMethodCommissionRates": [],
                "launchCountry": null,
                "abnormalStatusList": null,
                "closeReason": null,
                "storeInformation": null,
                "allowTradeMerchant": null
              },
              "advertiser": {
                "userNo": "s7e1ca5f4118338c59f9fc0b84f064480",
                "realName": null,
                "nickName": "RicklePick",
                "margin": null,
                "marginUnit": null,
                "orderCount": null,
                "monthOrderCount": 862,
                "monthFinishRate": 1.000,
                "positiveRate": 0.99550898,
                "advConfirmTime": null,
                "email": null,
                "registrationTime": null,
                "mobile": null,
                "userType": "merchant",
                "tagIconUrls": [],
                "userGrade": 3,
                "userIdentity": "MASS_MERCHANT",
                "proMerchant": null,
                "badges": [
                  "Ordinary"
                ],
                "isBlocked": false,
                "activeTimeInSecond": 17
              }
            }
          ],
          "total": 2,
          "success": true
        }
      """.trimIndent()
  }
}

fun WireMockServer.searchPage2SuitablePriceStub() {
  post {
    urlPath equalTo "/bapi/c2c/v2/friendly/c2c/adv/search"
    withBuilder { withRequestBody(MatchesJsonPathPattern("\$.page", EqualToPattern("2"))) }
  } returnsJson {
    body =
      // language=JSON
      """
        {
          "code": "000000",
          "message": null,
          "messageDetail": null,
          "data": [
            {
              "adv": {
                "advNo": "12562532875227787264",
                "classify": "2",
                "tradeType": "SELL",
                "asset": "USDT",
                "fiatUnit": "USD",
                "advStatus": null,
                "priceType": null,
                "priceFloatingRatio": null,
                "rateFloatingRatio": null,
                "currencyRate": null,
                "price": "1.015",
                "initAmount": null,
                "surplusAmount": "5853.26",
                "amountAfterEditing": null,
                "maxSingleTransAmount": "11500.00",
                "minSingleTransAmount": "100.00",
                "buyerKycLimit": null,
                "buyerRegDaysLimit": null,
                "buyerBtcPositionLimit": null,
                "remarks": null,
                "autoReplyMsg": "",
                "payTimeLimit": 15,
                "tradeMethods": [
                  {
                    "payId": null,
                    "payMethodId": "",
                    "payType": null,
                    "payAccount": null,
                    "payBank": null,
                    "paySubBank": null,
                    "identifier": "BankofGeorgia",
                    "iconUrlColor": null,
                    "tradeMethodName": "Bank of Georgia",
                    "tradeMethodShortName": null,
                    "tradeMethodBgColor": "#F26724"
                  }
                ],
                "userTradeCountFilterTime": null,
                "userBuyTradeCountMin": null,
                "userBuyTradeCountMax": null,
                "userSellTradeCountMin": null,
                "userSellTradeCountMax": null,
                "userAllTradeCountMin": null,
                "userAllTradeCountMax": null,
                "userTradeCompleteRateFilterTime": null,
                "userTradeCompleteCountMin": null,
                "userTradeCompleteRateMin": null,
                "userTradeVolumeFilterTime": null,
                "userTradeType": null,
                "userTradeVolumeMin": null,
                "userTradeVolumeMax": null,
                "userTradeVolumeAsset": null,
                "createTime": null,
                "advUpdateTime": null,
                "fiatVo": null,
                "assetVo": null,
                "advVisibleRet": null,
                "takerAdditionalKycRequired": 0,
                "assetLogo": null,
                "assetScale": 2,
                "fiatScale": 2,
                "priceScale": 3,
                "fiatSymbol": "${'$'}",
                "isTradable": true,
                "dynamicMaxSingleTransAmount": "5924.47",
                "minSingleTransQuantity": "98.52",
                "maxSingleTransQuantity": "11330.04",
                "dynamicMaxSingleTransQuantity": "5836.91",
                "tradableQuantity": "5836.91",
                "commissionRate": "0.00280000",
                "takerCommissionRate": null,
                "tradeMethodCommissionRates": [],
                "launchCountry": null,
                "abnormalStatusList": null,
                "closeReason": null,
                "storeInformation": null,
                "allowTradeMerchant": null
              },
              "advertiser": {
                "userNo": "s73b325a85c293895abc96e4e55711916",
                "realName": null,
                "nickName": "Mulkiye",
                "margin": null,
                "marginUnit": null,
                "orderCount": null,
                "monthOrderCount": 177,
                "monthFinishRate": 0.957,
                "positiveRate": 0.99024390,
                "advConfirmTime": null,
                "email": null,
                "registrationTime": null,
                "mobile": null,
                "userType": "merchant",
                "tagIconUrls": [],
                "userGrade": 3,
                "userIdentity": "MASS_MERCHANT",
                "proMerchant": null,
                "badges": [
                  "Ordinary"
                ],
                "isBlocked": false,
                "activeTimeInSecond": 2017
              }
            },
            {
              "adv": {
                "advNo": "11557622055826542592",
                "classify": "2",
                "tradeType": "SELL",
                "asset": "USDT",
                "fiatUnit": "USD",
                "advStatus": null,
                "priceType": null,
                "priceFloatingRatio": null,
                "rateFloatingRatio": null,
                "currencyRate": null,
                "price": "1.015",
                "initAmount": null,
                "surplusAmount": "7690.54",
                "amountAfterEditing": null,
                "maxSingleTransAmount": "15000.00",
                "minSingleTransAmount": "100.00",
                "buyerKycLimit": null,
                "buyerRegDaysLimit": null,
                "buyerBtcPositionLimit": null,
                "remarks": null,
                "autoReplyMsg": "",
                "payTimeLimit": 15,
                "tradeMethods": [
                  {
                    "payId": null,
                    "payMethodId": "",
                    "payType": null,
                    "payAccount": null,
                    "payBank": null,
                    "paySubBank": null,
                    "identifier": "BankofGeorgia",
                    "iconUrlColor": null,
                    "tradeMethodName": "Bank of Georgia",
                    "tradeMethodShortName": null,
                    "tradeMethodBgColor": "#F26724"
                  }
                ],
                "userTradeCountFilterTime": null,
                "userBuyTradeCountMin": null,
                "userBuyTradeCountMax": null,
                "userSellTradeCountMin": null,
                "userSellTradeCountMax": null,
                "userAllTradeCountMin": null,
                "userAllTradeCountMax": null,
                "userTradeCompleteRateFilterTime": null,
                "userTradeCompleteCountMin": null,
                "userTradeCompleteRateMin": null,
                "userTradeVolumeFilterTime": null,
                "userTradeType": null,
                "userTradeVolumeMin": null,
                "userTradeVolumeMax": null,
                "userTradeVolumeAsset": null,
                "createTime": null,
                "advUpdateTime": null,
                "fiatVo": null,
                "assetVo": null,
                "advVisibleRet": null,
                "takerAdditionalKycRequired": 0,
                "assetLogo": null,
                "assetScale": 2,
                "fiatScale": 2,
                "priceScale": 3,
                "fiatSymbol": "${'$'}",
                "isTradable": true,
                "dynamicMaxSingleTransAmount": "7784.10",
                "minSingleTransQuantity": "98.52",
                "maxSingleTransQuantity": "14778.32",
                "dynamicMaxSingleTransQuantity": "7669.06",
                "tradableQuantity": "7669.06",
                "commissionRate": "0.00280000",
                "takerCommissionRate": null,
                "tradeMethodCommissionRates": [],
                "launchCountry": null,
                "abnormalStatusList": null,
                "closeReason": null,
                "storeInformation": null,
                "allowTradeMerchant": null
              },
              "advertiser": {
                "userNo": "sb2e973f06f593178a6c92bbf0b5dfa6e",
                "realName": null,
                "nickName": "Almaz101",
                "margin": null,
                "marginUnit": null,
                "orderCount": null,
                "monthOrderCount": 721,
                "monthFinishRate": 0.998,
                "positiveRate": 1.00000000,
                "advConfirmTime": null,
                "email": null,
                "registrationTime": null,
                "mobile": null,
                "userType": "merchant",
                "tagIconUrls": [],
                "userGrade": 3,
                "userIdentity": "MASS_MERCHANT",
                "proMerchant": null,
                "badges": [
                  "Ordinary"
                ],
                "isBlocked": false,
                "activeTimeInSecond": 176
              }
            }
          ],
          "total": 2,
          "success": true
        }
      """.trimIndent()
  }
}

fun WireMockServer.searchPage1NonSuitablePriceStub() {
  post {
    urlPath equalTo "/bapi/c2c/v2/friendly/c2c/adv/search"
    withBuilder { withRequestBody(MatchesJsonPathPattern("\$.page", EqualToPattern("1"))) }
  } returnsJson {
    body =
      // language=JSON
      """
        {
          "code": "000000",
          "message": null,
          "messageDetail": null,
          "data": [
            {
              "adv": {
                "advNo": "12557690784292093952",
                "classify": "2",
                "tradeType": "SELL",
                "asset": "USDT",
                "fiatUnit": "USD",
                "advStatus": null,
                "priceType": null,
                "priceFloatingRatio": null,
                "rateFloatingRatio": null,
                "currencyRate": null,
                "price": "1.016",
                "initAmount": null,
                "surplusAmount": "13530.63",
                "amountAfterEditing": null,
                "maxSingleTransAmount": "20000.00",
                "minSingleTransAmount": "500.00",
                "buyerKycLimit": null,
                "buyerRegDaysLimit": null,
                "buyerBtcPositionLimit": null,
                "remarks": null,
                "autoReplyMsg": "",
                "payTimeLimit": 15,
                "tradeMethods": [
                  {
                    "payId": null,
                    "payMethodId": "",
                    "payType": null,
                    "payAccount": null,
                    "payBank": null,
                    "paySubBank": null,
                    "identifier": "BankofGeorgia",
                    "iconUrlColor": null,
                    "tradeMethodName": "Bank of Georgia",
                    "tradeMethodShortName": null,
                    "tradeMethodBgColor": "#F26724"
                  }
                ],
                "userTradeCountFilterTime": null,
                "userBuyTradeCountMin": null,
                "userBuyTradeCountMax": null,
                "userSellTradeCountMin": null,
                "userSellTradeCountMax": null,
                "userAllTradeCountMin": null,
                "userAllTradeCountMax": null,
                "userTradeCompleteRateFilterTime": null,
                "userTradeCompleteCountMin": null,
                "userTradeCompleteRateMin": null,
                "userTradeVolumeFilterTime": null,
                "userTradeType": null,
                "userTradeVolumeMin": null,
                "userTradeVolumeMax": null,
                "userTradeVolumeAsset": null,
                "createTime": null,
                "advUpdateTime": null,
                "fiatVo": null,
                "assetVo": null,
                "advVisibleRet": null,
                "takerAdditionalKycRequired": 0,
                "assetLogo": null,
                "assetScale": 2,
                "fiatScale": 2,
                "priceScale": 3,
                "fiatSymbol": "${'$'}",
                "isTradable": true,
                "dynamicMaxSingleTransAmount": "13681.74",
                "minSingleTransQuantity": "493.09",
                "maxSingleTransQuantity": "19723.86",
                "dynamicMaxSingleTransQuantity": "13492.85",
                "tradableQuantity": "13492.85",
                "commissionRate": "0.00280000",
                "takerCommissionRate": null,
                "tradeMethodCommissionRates": [],
                "launchCountry": null,
                "abnormalStatusList": null,
                "closeReason": null,
                "storeInformation": null,
                "allowTradeMerchant": null
              },
              "advertiser": {
                "userNo": "s4a9e298d0d7c327dbfcc5c0b32d95895",
                "realName": null,
                "nickName": "Zura",
                "margin": null,
                "marginUnit": null,
                "orderCount": null,
                "monthOrderCount": 44,
                "monthFinishRate": 1.000,
                "positiveRate": 0.99978880,
                "advConfirmTime": null,
                "email": null,
                "registrationTime": null,
                "mobile": null,
                "userType": "merchant",
                "tagIconUrls": [],
                "userGrade": 3,
                "userIdentity": "MASS_MERCHANT",
                "proMerchant": null,
                "badges": [
                  "Ordinary"
                ],
                "isBlocked": false,
                "activeTimeInSecond": 237
              }
            },
            {
              "adv": {
                "advNo": "11555491607579504640",
                "classify": "2",
                "tradeType": "SELL",
                "asset": "USDT",
                "fiatUnit": "USD",
                "advStatus": null,
                "priceType": null,
                "priceFloatingRatio": null,
                "rateFloatingRatio": null,
                "currencyRate": null,
                "price": "1.017",
                "initAmount": null,
                "surplusAmount": "4373.19",
                "amountAfterEditing": null,
                "maxSingleTransAmount": "15000.00",
                "minSingleTransAmount": "150.00",
                "buyerKycLimit": null,
                "buyerRegDaysLimit": null,
                "buyerBtcPositionLimit": null,
                "remarks": null,
                "autoReplyMsg": "",
                "payTimeLimit": 15,
                "tradeMethods": [
                  {
                    "payId": null,
                    "payMethodId": "",
                    "payType": null,
                    "payAccount": null,
                    "payBank": null,
                    "paySubBank": null,
                    "identifier": "BankofGeorgia",
                    "iconUrlColor": null,
                    "tradeMethodName": "Bank of Georgia",
                    "tradeMethodShortName": null,
                    "tradeMethodBgColor": "#F26724"
                  },
                  {
                    "payId": null,
                    "payMethodId": "",
                    "payType": null,
                    "payAccount": null,
                    "payBank": null,
                    "paySubBank": null,
                    "identifier": "UNKNOWNbank",
                    "iconUrlColor": null,
                    "tradeMethodName": "Unknown bank",
                    "tradeMethodShortName": null,
                    "tradeMethodBgColor": "#F26725"
                  }
                ],
                "userTradeCountFilterTime": null,
                "userBuyTradeCountMin": null,
                "userBuyTradeCountMax": null,
                "userSellTradeCountMin": null,
                "userSellTradeCountMax": null,
                "userAllTradeCountMin": null,
                "userAllTradeCountMax": null,
                "userTradeCompleteRateFilterTime": null,
                "userTradeCompleteCountMin": null,
                "userTradeCompleteRateMin": null,
                "userTradeVolumeFilterTime": null,
                "userTradeType": null,
                "userTradeVolumeMin": null,
                "userTradeVolumeMax": null,
                "userTradeVolumeAsset": null,
                "createTime": null,
                "advUpdateTime": null,
                "fiatVo": null,
                "assetVo": null,
                "advVisibleRet": null,
                "takerAdditionalKycRequired": 0,
                "assetLogo": null,
                "assetScale": 2,
                "fiatScale": 2,
                "priceScale": 3,
                "fiatSymbol": "${'$'}",
                "isTradable": true,
                "dynamicMaxSingleTransAmount": "4426.96",
                "minSingleTransQuantity": "147.92",
                "maxSingleTransQuantity": "14792.89",
                "dynamicMaxSingleTransQuantity": "4365.55",
                "tradableQuantity": "4365.55",
                "commissionRate": "0.00175000",
                "takerCommissionRate": null,
                "tradeMethodCommissionRates": [],
                "launchCountry": null,
                "abnormalStatusList": null,
                "closeReason": null,
                "storeInformation": null,
                "allowTradeMerchant": null
              },
              "advertiser": {
                "userNo": "s7e1ca5f4118338c59f9fc0b84f064480",
                "realName": null,
                "nickName": "RicklePick",
                "margin": null,
                "marginUnit": null,
                "orderCount": null,
                "monthOrderCount": 862,
                "monthFinishRate": 1.000,
                "positiveRate": 0.99550898,
                "advConfirmTime": null,
                "email": null,
                "registrationTime": null,
                "mobile": null,
                "userType": "merchant",
                "tagIconUrls": [],
                "userGrade": 3,
                "userIdentity": "MASS_MERCHANT",
                "proMerchant": null,
                "badges": [
                  "Ordinary"
                ],
                "isBlocked": false,
                "activeTimeInSecond": 17
              }
            }
          ],
          "total": 2,
          "success": true
        }
      """.trimIndent()
  }
}

fun WireMockServer.searchPage1EmptyStub() {
  post {
    urlPath equalTo "/bapi/c2c/v2/friendly/c2c/adv/search"
    withBuilder { withRequestBody(MatchesJsonPathPattern("\$.page", EqualToPattern("1"))) }
  } returnsJson {
    body =
      // language=JSON
      """
        {
          "code": "000000",
          "message": null,
          "messageDetail": null,
          "data": [],
          "total": 0,
          "success": true
        }
      """.trimIndent()
  }
}
