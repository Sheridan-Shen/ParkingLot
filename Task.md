# Task
## 一、停车
### AC1
Given a parking_lot and a car 
When park a car
Then success message and a ticket

### AC2
Given a park and a car and no position
When park a car
Then failure message

### AC3
Given a park and a parked car
When park a car
Then failure message

### AC4
Given a park and null
When park a car
Then failure message

## 二、取车
### AC5
Given a park and a ticket
When fetch a car
Then success and a car

### AC6
Given a park and a wrong ticket
When fetch a car
Then failure

### AC7
Given a park and without a ticket
When fetch a car
Then failure

## 三、重复取车
### AC8
Given a park and a used ticket
When fetch a car
Then failure