# Energy Epicode

## Rest API

### Authentication

TODO

### Customers

```
GET /api/customers/{params}

orderByCompanyName
orderByannualTurnover
orderByinsertionDate
orderBylastContactDate
orderByprovince

filterByMinAnnualTurnover
filterByMaxAnnualTurnover
filterByMinInsertionDate
filterByMaxInsertionDate
filterByMinLastContactDate
filterByMaxLastContactDate
filterByCompanyName

page
size

{
    "companyName": ,
    "contactName": ,
    "contactSurname": ,
    "contactPhone": ,
    "contactEmail": ,
    "address": ,
    "logo": ,
}
```

```
GET /api/customers/{id}/

{
    "companyName": ,
    "contactName": ,
    "contactSurname": ,
    "contactPhone": ,
    "contactEmail": ,
    "logo":
    "annualTurnover": ,
    "vatNumber": ,
    "insertionDate": ,
    "lastContactDate": ,
    "pec": ,
    "email": ,
    "phone": ,
    "address": ,
}
```

```
POST /api/customers/

{
    "companyName": ,
    "contactName": ,
    "contactSurname": ,
    "contactPhone": ,
    "contactEmail": ,
    "logo":
    "annualTurnover": ,
    "vatNumber": ,
    "insertionDate": ,
    "lastContactDate": ,
    "pec": ,
    "email": ,
    "phone": ,
    "address": ,
}
```

```
PATCH /api/customers/{id}/

{
    "companyName": ,
    "contactName": ,
    "contactSurname": ,
    "contactPhone": ,
    "contactEmail": ,
    "logo":
    "annualTurnover": ,
    "vatNumber": ,
    "insertionDate": ,
    "lastContactDate": ,
    "pec": ,
    "email": ,
    "phone": ,
    "address": ,
}
```

```
DELETE /api/customers/{id}/
```

### Invoices

```
GET /api/customers/invoices/{params}

filterByCustomerName
filterByStatus
filterByMinDate
filterByMaxDate
filterByMinAmount
filterByMaxAmount

page
size

{
    "date": ,
    "amount": ,
    "number": ,
    "status": ,
}
```

```
GET /api/customers/invoices/{id}/

{
    "date": ,
    "amount": ,
    "number": ,
    "status": ,
}
```

```
POST /api/customers/invoices/

{
    "date": ,
    "amount": ,
    "number": ,
    "status": ,
}
```

```
PATCH /api/customers/invoices/{id}/

{
    "date": ,
    "amount": ,
    "number": ,
    "status": ,
}
```

```
DELETE /api/customers/invoices/{id}/
```