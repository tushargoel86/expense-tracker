# expense-tracker

Expense tracking app to track the expenses

Technology used:
React & Hooks,
React-Bootstrap,
Java & Spring Boot,
PostgressSQL,

GUI:

![](https://github.com/tushargoel86/expense-tracker/blob/master/images/start.PNG)
![](https://github.com/tushargoel86/expense-tracker/blob/master/images/AddTransaction.PNG)
![](https://github.com/tushargoel86/expense-tracker/blob/master/images/Expense.PNG)



API:

1) Create a User using postman
POST : http://localhost:9090/user
```
{
	"userName": "Tushar Goel",
	"email": "tushargoel86@gmail.com"
}
```

2) List User:
GET:  http://localhost:9090/users/v2/{email}

3) You can now use expense APIs for expense

POST: http://localhost:8080/expense
```
{
"startDate": "2020-04-01",
"endDate": "2020-04-30",
"transactionDate": "2020-04-19",
"amount": "100",
"transactionType": "RECEIVED",
"description": "Cashback",
"tag": "Saving",
"userId": "{{userId}}"
}
```
4) List expenses
```
POST: http://localhost:8080/
{
 "startDate": "2020-04-01",
 "endDate" : "2020-04-30",
 "userId": "{{userId}}"
}
```

5) Delete expense: You can get variable details through list expense call
```
DELETE: http://localhost:8080/periods/{periodId}/users/{userId}/tags/{tagId}/transactions/{transactionId}
```

Further Improvements:
1) Add Login page
2) Authentication using openid
3) Deploy on AWS
4) Convert into microservices
