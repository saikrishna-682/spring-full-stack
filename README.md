# TODO
Docker Postgres sql 

Docker-compose.yml file 


services:
  db:
    container_name: postgres
    image: postgres
    environment:
      POSTGRES_USER: amigoscode
      POSTGRES_PASSWORD: password
      PGDATA: /data/postgres
    volumes:
      - postgres:/data/postgres
    ports:
      - "5432:5432"
    networks:
      - postgres
    restart: unless-stopped
  pgadmin:
    container_name: pgadmin
    image: dpage/pgadmin4
    environment:
      PGADMIN_DEFAULT_EMAIL: ${PGADMIN_DEFAULT_EMAIL:-pgadmin4@pgadmin.org}
      PGADMIN_DEFAULT_PASSWORD: ${PGADMIN_DEFAULT_PASSWORD:-admin}
      PGADMIN_CONFIG_SERVER_MODE: 'False'
    volumes:
      - pgadmin:/var/lib/pgadmin
    ports:
      - "5050:80"
    networks:
      - postgres
    restart: unless-stopped
    depends_on:
      - db

networks:
  postgres:
    driver: bridge

volumes:
  postgres:
  pgadmin:





docker compose ps or docker ps
docker exec -it postgres bash
psql -U amigoscode




Application properties for the spring to connect poster configuration 
server:
  port: 8080
  error:
    include-message: always

spring:
  datasource:
    url: jdbc:postgresql://localhost:5332/customer
    username: amigoscode
    password: password
  jpa:
    hibernate:
      ddl-auto: create-drop
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
    show-sql: true
  main:
    web-application-type: servlet







📝 PSQL Commands Cheatsheet
👉 To quit psql: \q 👉 To list databases: \l 👉 To connect to a database: \c mydatabase 👉 To list tables: \dt




👉 To create a new database: CREATE DATABASE mydatabase; 👉 To select data from a table: SELECT * FROM mytable;




👉 To insert data into a table: INSERT INTO mytable (name) VALUES ('John');
👉 To select data from a table: SELECT * FROM mytable;
👉 To update data in a table: UPDATE mytable SET name = 'Jane' WHERE id = 1;
👉 To delete data from a table: DELETE FROM mytable WHERE id = 1;



PostgreSQL provides the following SQL commands to work with sequences:

CREATE SEQUENCE: This command is used to create a new sequence. The syntax for creating a sequence is as follows:

CREATE SEQUENCE sequence_name 
  [ INCREMENT increment ]
  [ MINVALUE min_value ]
  [ MAXVALUE max_value ]
  [ START start ]
  [ CACHE cache ]
  [ CYCLE | NO CYCLE ];
  
sequence_name: The name of the sequence to be created.
INCREMENT: The amount by which the sequence is incremented. The default is 1.
MINVALUE: The minimum value of the sequence. The default is 1.
MAXVALUE: The maximum value of the sequence. The default is the largest value of the data type.
START: The starting value of the sequence. The default is the minimum value of the data type.
CACHE: The number of sequence values to cache. The default is 1.
CYCLE | NO CYCLE: Specifies whether the sequence should start over when the maximum or minimum value is reached.
NEXTVAL: This command is used to get the next value from a sequence. The syntax for getting the next value from a sequence is as follows:

---------------

SELECT NEXTVAL('sequence_name'); 
sequence_name: The name of the sequence from which to get the next value.
CURRVAL: This command is used to get the current value of a sequence. The syntax for getting the current value of a sequence is as follows:

---------------

SELECT CURRVAL('sequence_name'); 
sequence_name: The name of the sequence from which to get the current value.
SETVAL: This command is used to set the current value of a sequence. The syntax for setting the current value of a sequence is as follows:

---------------

SELECT SETVAL('sequence_name', new_value); 
sequence_name: The name of the sequence to set the current value for.
new_value: The new value to set for the sequence.
DROP SEQUENCE: This command is used to delete a sequence. The syntax for deleting a sequence is as follows:

---------------

DROP SEQUENCE sequence_name; 
sequence_name: The name of the sequence to be deleted.
These are the basic SQL commands for working with sequences in PostgreSQL.




DOCKER USE of FORMAT export FORMAT="ID\t{{.ID}}\nNAME\t{{.Names}}\nIMAGE\t{{.Image}}\nPORTS\t{{.Ports}}\nCOMMAND\t{{.Command}}\nCREATED\t{{.CreatedAt}}\nSTATUS\t{{.Status}}\n"
Complete and Continue
 



Here are some common algorithms used for SSH keys:
1. RSA (Rivest–Shamir–Adleman) - This is the most widely used algorithm for SSH keys. It is a public-key cryptosystem that generates a pair of keys, one public and one private. The public key is used for encryption, while the private key is used for decryption.
2. DSA (Digital Signature Algorithm) - This is another public-key cryptosystem used for SSH keys. It is similar to RSA, but is generally considered to be less secure and has smaller key sizes.
3. ECDSA (Elliptic Curve Digital Signature Algorithm) - This is a newer algorithm used for SSH keys. It is based on elliptic curve cryptography and is considered to be more secure than DSA.
4. Ed25519 - This is a high-security public-key algorithm used for SSH keys. It is based on elliptic curve cryptography and is considered to be faster and more secure than RSA and DSA.
When generating an SSH key, you can usually choose which algorithm to use. The choice of algorithm depends on a number of factors, including the level of security required, the performance of the system, and the compatibility of the key with other systems.




 SSH Keys
SSH keys are a form of authentication used in Secure Shell (SSH) protocol for establishing a secure connection between two computers.
When you connect to an SSH server, the server asks you to authenticate yourself. Typically, this involves entering a username and password. However, SSH keys provide an alternative and more secure way to authenticate.
An SSH key is a pair of cryptographic keys, consisting of a public key and a private key. The public key is stored on the SSH server, while the private key is stored on your local computer. When you connect to the server, the server sends a challenge message, which your computer signs using the private key and sends back to the server. The server verifies the signature using the corresponding public key, and if the signature is valid, the connection is established.
The advantage of using SSH keys over passwords is that they are much more secure. Passwords can be guessed or intercepted, but a properly generated SSH key pair is virtually impossible to crack. Additionally, SSH keys eliminate the need for users to remember and enter passwords each time they connect to a server.
To generate an SSH key pair, you can use the "ssh-keygen" command in a terminal. The public key should be uploaded to the SSH server, while the private key should be kept on your local computer and protected with a strong passphrase.


ssh "permissions are too open" 🤔
The error message "permissions are too open" occurs when the permissions on your SSH key files are too permissive, meaning they allow read or write access to users who should not have access. This can happen when the file permissions are set to 777 or similar values.
To fix this error, you can change the permissions on the SSH key files using the following command in your terminal:
chmod 600 <path-to-ssh-key>
 This command sets the permissions on the SSH key file to 600, which means that only the owner of the file has read and write access, and no one else can read or write to the file.
If you still get the error message after changing the file permissions, you may also need to check the permissions on the directory that contains the SSH key files. The directory should have permissions set to 700, which means that only the owner can read, write, and access the directory.
chmod 700 <path-to-directory>
 After changing the permissions on both the SSH key files and the directory that contains them, try connecting to your SSH server again. The error message should no longer appear.


PATH


ssh -i your-private-key.pem ec2-user@instance-public-ip-address







Git Cheatsheet

Command Description

git init Initializes a new Git repository.
git clone [url] Creates a copy of a remote repository on your local machine.
git add [file] Adds changes in a file to the staging area for the next commit.
git commit -m "[message]" Records changes to the repository with a message describing the changes.
git status Displays the current status of the repository, including any uncommitted changes.
git branch Lists all branches in the repository, indicating the current branch with an asterisk.
git checkout [branch] Switches to a specified branch.
git merge [branch] Merges a specified branch into the current branch.
git push [remote] [branch] Uploads local changes to a remote repository.
git pull [remote] [branch] Downloads and integrates changes from a remote repository into the current branch.
git remote add [name] [url] Adds a named remote repository to the list of tracked repositories.
git remote -v Displays a list of all remote repositories associated with the local repository.
git log Displays a log of all commits made to the repository.
git diff Displays the differences between the working directory and the staging area or the most recent commit.
git reset [file] Removes changes from the staging area for a specified file.
git stash Saves changes in the working directory toa stash, allowing you to switch branches or perform other operations without committing the changes.
git stash apply Restores the most recent stash to the working directory.
git tag [name] Adds a tag to the most recent commit.
git tag -a [name] -m "[message]" Adds an annotated tag to the most recent commit with a message describing the tag.
git show [commit] Displays the details of a specified commit.
git rm [file] Removes a specified file from the repository and stages the change for the next commit.
git mv [old] [new] Renames a specified file and stages the change for the next commit.
git remote rm [name] Removes a specified remote repository from the list of tracked repositories.
git help [command] Displays help information for a specified Git command.





DOCKER ACCESS TOKEN 
dckr_pat_9OkouVaJyKpOE7MFAKb8P9K2BB4




FRONTEND Scripts :: 

In a Node.js project, the package.json file is used to define various aspects of the project, including its dependencies, version, and metadata. One of the most important aspects of the package.json file is the "scripts" field, which allows you to define custom scripts that can be run using the npm or yarn commands.
Scripts are essentially commands or tasks that can be executed by running a specific npm or yarn command followed by the script name. For example, if you have a script named "start" defined in your package.json file, you could run it by typing "npm start" or "yarn start" in the command line.
The "scripts" field is an object where each key represents the name of the script, and each value represents the command or commands to be executed when the script is run. The command can be a simple shell command or a complex command that involves multiple commands or tools. You can also pass arguments to the command by using placeholders such as $1, $2, etc.
Some common scripts that are often defined in a package.json file include:
* start: Runs the application in development mode.
* test: Runs the tests for the application.
* build: Builds the application for production.
Custom scripts can also be defined based on the specific needs of the project. The "scripts" field provides a flexible and powerful way to define and run custom commands or tasks as part of the project's development, testing, or deployment process.




 In a project where Vite is installed, you can use the vite binary in your npm scripts, or run it directly with npx vite. Here are the default npm scripts in a scaffolded Vite project:
{
  "scripts": {
    "dev": "vite", // start dev server, aliases: vite dev, vite serve
    "build": "vite build", // build for production
    "preview": "vite preview" // locally preview production build
  }
}

You can specify additional CLI options like --port or --https. For a full list of CLI options, run npx vite --help in your project.



What is CORS :

 
CORS stands for "Cross-Origin Resource Sharing." It is a security feature implemented in web browsers to prevent malicious websites from making unauthorized requests to a different website's resources.
When a web page makes a request to a different domain (i.e., a different origin), the browser checks to see if the server hosting the requested resource allows the requesting domain to access it. This is done through the use of HTTP headers that are sent with the request and the response.
If the server allows the requesting domain to access the resource, the browser allows the request to proceed and returns the requested data to the original web page. If the server does not allow the requesting domain to access the resource, the browser blocks the request and returns an error.
The CORS mechanism is important because it prevents malicious websites from stealing sensitive information or performing actions on behalf of a user on a different website. For example, if a user is logged in to their bank account on one website and then visits a malicious website that attempts to make requests to the bank's server, the CORS mechanism will prevent the requests from succeeding.

