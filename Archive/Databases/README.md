# Relational Databases

## Connecting via Terminal

If you are off-campus, you must first SSH into a lab computer. See the [Logging in Remotely](http://tutoringcenter.cs.usfca.edu/resources/logging-in-remotely.html) guide for instructions.

After you have connected to a lab computer, use the following command to login to our MySQL server:

    mysql -h sql.cs.usfca.edu -u sqluser -D sqluser -p

where `sqluser` is your assigned MySQL username.

## Changing Password

You know you are properly logged into the MySQL server when you see the `mysql>` prompt. To change your password, use the following command at the prompt:

    SET PASSWORD = PASSWORD('newpass');

where `newpass` is your new password. Save this password! If you forget this password, you must contact [CS Support](mailto:support@cs.usfca.edu) to reset your account.

## Loading Examples

All SQL examples from class are located in the following directory on the lab computers:

    /home/public/cs212/sql/

If you want to load the tables into your database from those files, you should use the `source` command. For example, enter the following command at the `mysql>` prompt:

    source /home/public/cs212/sql/contact.sql

This will load all of the tables from the `contact_users` example shown in class. (If you run this command twice, it will fail because the tables are already created.)

## Configuring JDBC

Modify `database.properties` with your MySQL username and password. If you
are connecting from on-campus, then set `hostname=sql.cs.usfca.edu` in the
properties file.

If you are connecting off-campus, then set `hostname=localhost:3307` in the
properties file, and create an SSH tunnel to forward anything sent to your
port 3307 to our MySQL server on campus. The `tunnel.command` file contains
the SSH command you should use (except you must change `username` to your
CS username).
