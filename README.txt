FX Calculator is a console-based currency converter application

The application allows a user to convert an amount in a specific currency to the equivalent amount in another currency.

On compilation of this project you will get executable jar.
To compile use > mvn clean install

Once compiled fxcalculator-jar-with-dependencies.jar will be created under target directory. target\fxcalculator-jar-with-dependencies.jar
Place the feed properties along with the jar file. There are 2 feed properties file namely currencymap.properties and currencydecimal.properties.

currencymap.properties contains currency map Eg:
AUDUSD=0.8371
CADUSD=0.8711
USDCNY=6.1715
EURUSD=1.2315
GBPUSD=1.5683
NZDUSD=0.7750
USDJPY=119.95
EURCZK=27.6028
EURDKK=7.4405
EURNOK=8.6651 

currencydecimal.properties contains decimal values to be displayed for currency.
AUD=2 decimal places
CAD=2 decimal places
CNY=2 decimal places
CZK=2 decimal places
DKK=2 decimal places
EUR=2 decimal places
GBP=2 decimal places
JPY=0 decimal places
NOK=2 decimal places
NZD=2 decimal places
USD=2 decimal places

To execute use the following command > java -jar fxcalculator-jar-with-dependencies.jar
Input should be provided in <ccy1> <amount1> in <ccy2> format.
Sample output
#############################################################################################################################
D:\workspace12\fxcalculator\target>java -jar fxcalculator-jar-with-dependencies.jar
Input formats.
1. For exchange rate: <ccy1> <amount1> in <ccy2>
2. To update currency rate: Reload exchange rate
To quit enter q
%> AUD 100 in USD
AUD 100 = USD 83.71
%> AUD 100 in AUD
AUD 100 = AUD 100.00
%> AUD 100 in DKK
AUD 100 = DKK 505.76
%> JPY 100 in USD
JPY 100 = USD 0.83
%> KRW 1000.00 in FJD
Unable to find rate for KRW/FJD
%> AUD in USD
Invalid input format. Please enter in below format
<ccy1> <amount1> in <ccy2>
%> q
Exit!
##############################################################################################################################



Reload exchange rate: change the exchange rate in the properties file and input "Reload exchange rate" it reload the cache 
and subsequent request will have exchanged rate. Here you can not only chnage the exchange rate of existing currency but also 
can add new conversions. Also decimal properties can be changed, it will also get reloaded.
Below is an example when initially feed had AUDUSD=0.8371 then changed to AUDUSD=1  
##############################################################################################################################
D:\workspace12\fxcalculator\target>java -jar fxcalculator-jar-with-dependencies.jar
Input formats.
1. For exchange rate: <ccy1> <amount1> in <ccy2>
2. To update currency rate: Reload exchange rate
To quit enter q
%> AUD 100 in USD
AUD 100 = USD 83.71
%> Reload exchange rate
%> AUD 100 in USD
AUD 100 = USD 100
%> AUD 100 in DKK
AUD 100 = DKK 604.18

To change the logging details like debug level/file name.
Please place the appropriate log4j.properties along with the jar. And use following command.
> java -jar -Dlog4j.configuration=file:log4j.properties fxcalculator-jar-with-dependencies.jar
  