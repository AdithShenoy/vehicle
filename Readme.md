Vehicle API
============
 
### Build
`mvn clean install`

## Functions

Vehicle API provides following functionality:

### Localize Vehicle Data

POST /localize-vehicle-data

- Reads a CSV with a UTC datetime, latitude and longitude columns and appends the timezone the vehicle is in and the localised datetime. 
- Communicates with Google TimeZone API to fetch timezone data based on latitude and longitude.

### Validations
- Allows a minimum of 2 maximum of 1000 lines in csv to be uploaded
- Validates CSV header on first line of CSV

### Sample Request:
UTC_DATE_TIME,LATITUDE,LONGITUDE
2013-07-10 02:52:49,-44.490947,171.220966
2013-07-10 02:52:49,-33.912167,151.215820

### Sample Response:
2013-07-10 02:52:49,-44.490947,171.220966,Pacific/Auckland,2013-07-10T14:52:49
2013-07-10 02:52:49,-33.912167,151.215820,Australia/Sydney,2013-07-10T12:52:49