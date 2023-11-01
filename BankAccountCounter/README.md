# Bank Account Counter

A compressed JSON file containing a bank's current accounts is given.

Each current account contains the account holder's name and a list of transactions.<br>
The movements recorded for a current account can be very numerous.<br>
For each movement, the date and reason for the movement are recorded.<br>
The set of possible reasons is fixed: Bank transfer, Credit, Bulletin, F24, PagoBancomat.<br>

Design an application that triggers a set of threads.<br>
One of them reads the "current account" objects from the file and passes them, one at a time, to the threads present in a thread pool.<br>
We want to find, for each possible reason, how many movements that reason has.<br>
Reading from the file must be done using the GSON API for streaming.<br>

Expected results:<br>
F24: 3998118<br>
PAGOBANCOMAT: 3996664<br>
ACCREDITO: 3999846<br>
BONIFICO: 4001414<br>
BOLLETTINO: 4003958<br>

## Usage

- Extract **_accounts.json.zip_** in accounts folder

- Compile

```
javac -cp ./lib/gson-2.10.1.jar ./src/*.java
```

- Run

```
java -cp ./lib/gson-2.10.1.jar:. src.BankAccountCounter <number of threads>
```
