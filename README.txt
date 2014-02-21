																	CSC-573 – Internet Protocols
																		EAGLE EYE/DTEXTR*
																Functional specifications and design flow
															  {sdevara2, hamlipa, vstendul, ssasidh}@ncsu.edu


The document explains the features of the implemented P2P application. All the performance metrics and 
comparisons are also documented.

																		FEATURES

Peer (Common to both Centralised and Distributed)
For the peer, the following features are implemented / provided

? Registration
User needs to register with the bootstrap server the first time it connects to the server.
Full Name, Username, Designation, Password (with minimum length of 6 characters), Email address should be 
provided
These details are stored in the Bootstrap server. The password is AES encrypted.

? Login / Logout and Forgot / Change password
User can “login” to the system after registration using his username and password. This feature will add 
security to the P2P system 
If the user forgets his password, once the username is provided, a new password will be emailed to his 
registered Email ID
If the user wants to change his password, this can be done through the “Settings” option in the “Search” Page
User can “logout” to exit the application

? Publish
The files will be published automatically once the user logs into the application
User can change the folder which he plans to share using the “Settings” option
If any changes are made to the shared files, they can be published again to the server using “Publish” option in 
the “Search” page.
Spell check is not implemented due to lack of time

? Search and Download
User can search for the files in the P2P system and download the required files.
The following points will explain the design flow
o User must know the IP address of the boot strap server to connect to the file sharing system
o Once he connects to the system, he needs to register to login and access the files shared
o By default, the text files present in the “$User_home/publish” folder will be sent to the server to 
share. The user is provided with an option to change this setting in the setting page. 
o The search string entered by the user will be hashed and sent to the server
o The results will be displayed five per page with Next, Previous buttons to navigate. The name of the 
file, abstract and peer IP details and download option will be provided

											Boot strap Server (Centralised and Distributed)
The following modules will be present
? Stores the data about registered users (passwords are encrypted using AES)
? Stores the information about the files (filename, file digest, abstract, file size, IP address of the 
peer sharing) published by the peers
? Whenever forgot password request is received, mails a new password
BOOT STRAP SERVER CENTRALISED
? Stores the information about the files (filename, file digest, abstract, file size, IP address of the 
peer sharing) published by the peers
? Track users online on the system? Once the user logs in enter his name in a hash map and once he logs out remove the files shared 
by him
? When the search string is received as a hash, the server will search through the database and 
collect the results matching the search query parameter. This parameter indicates the percentage 
of match between the search query and the file digest.
? Page rank the results obtained and send them back to the client
BOOT STRAP SERVER DISTRIBUTED
? Only responsible for the common features mentioned above
? Sends an IP address to the successfully logged in peer to contact so that the peer can join the 
hash space of the system
Peer (Distributed)
Apart from the common features full functional implementations are different in distributed system
? After logging in, peer receives an IP address to contact from the boot strap server. Peer will now 
send a Join request to the IP address received
? Once the Join request is successful the peer’s files from the folder will be published to the various 
peers handling the hash spaces
? When the peer searches for the documents, the query is sent to peer managing the relevant hash 
space
? If the user wants more results the query is now sent to the neighbouring peers based on ’ =>’ 
property
? The page rank algorithm is now implemented at each peer before displaying the results to the 
user
CENTRALISED SYSTEM ARCHITECTURE
PEER
Every peer has 3 layers ? GUI, Control layer and Communication layer
GUI will provide the page where the user can interact and use various options provided in this application. 
Based on the user actions trigger events. Modules -- Register, login, logout, forgot password, change password, 
search, publish, and download.
Control layer
The main responsibility is to act as a manager between GUI and Communication layer
Flow
? Has individual modules which deal with various functionalities provided
? Each module is independent and responsible for handling the events and generating the objects to be 
sent to communication layer and also wait for the response and based on the responses update the 
GUI
? Local form validations will be done for modules required (register and login)
Communication layer
Responsibilities
XML messages are used for communication
? Connect to Boot strap server (Open a TCP connection on an IP address with a port number) when 
register is sent
? Receives “synchronous and asynchronous objects” as input and pass on the data to communication 
layer of the server in XML format
? When the response for the above requests is received, parse the object and send it to above control 
layerFlow
? Must open TCP connection with Boot Strap server when connect is sent
? It will start listening on a port (as a server) to send files to other peers who request them. Whenever a 
request for the file download comes, it will call a function in peer layer.
? For publishing we will make use of the port in point 2 (as it will be free when files are not requested)
? Unique request ID (system clock in msec) should be used for the XML messages. The formats for 
various messages are shown later.
BOOT STRAP SERVER
? Receives the requests from various peers and processes them and sends the responses
? For a ‘register’ request, checks if the information provided is valid and updates its user database and 
sends back the response
? For ‘login’ request, checks if the username and password provided are correct and sends a success 
message / exception
? For ‘Publish’ request, receives the files from the peer and updates its ‘file repository’
? For ‘Search’ request, matches the query with the files digest and page ranks the results and sends 
them to the peer
? For ‘Forgot password’ request, sends a new password to register username’s email ID
? For ‘Change password’ request updates its database with the new password
? For ‘logout’ request removes the files published by this user and sends back a success response
Since the boot strap server is the only server maintaining the files and user information, it is expected to 
receive a lot of requests from the peers. To handle these requests efficiently, threads are spawned for 
each user request which will remain active until the request is executed and response is sent.
PEER ARCHITECTURE
Figure 1: Peer
GUI Control Layer Communication 
Layer
Bootstrap Server
Request Thread
Response ThreadBOOT STRAP SERVER (CENTRALISED) ARCHITECTURE
Figure 2: Boot Strap Server
DISTRIBUTED SYSTEM ARCHITECTURE
PEER
The peer will have enhanced responsibilities in Distributed System. The Boot strap server will now only 
maintain the User information for logging into the system. The files shared by the peers are maintained by a 
set of peers which a assigned a part of the Hash space. Similar to Centralised the peer will have 3 layers, GUI, 
Control and Communication layer. The user management part is the same in both systems.
In the Distributed system, the hash space is managed by the peers and not by single boot strap server. Initially 
when there is a single peer registered with the boot strap server it will manage the entire hash space. When a 
new peer joins the system, it will initially log in with the boot strap server which will provide an IP address to 
contact. The new peer will contact the peer managing the hash space and initiate a ‘join’ request. When a 
‘join’ request is received, the peer will split its hash space in a particular direction and respond back with the 
zone co-ordinates, files in that hash space along with its current routing table (which is built based on ‘=>’ 
relation). The new peer will now update its routing table and store the files in the repository. The routing table 
is maintained as per the overlay grid network suggested in A. Lal, V. Gupta, K. Harfoush and I. Rhee, "Towards 
the Feasibility and Scalability of Text Search in Peer-to-Peer Systems" paper.
Once the peer joins the system and obtains the hash space it is responsible for, the files from the new peer will 
be shared (publish) with the system. Each file is hashed and based on the file digest, the files are sent to the 
peers managing the relevant hast space/zone.
When user searches for a query, the peer will direct the peer will first check if the query digest is within the 
hash space maintained by it. If yes, it obtains the results, ranks them and displays them to the user. Otherwise 
the request is sent to the peer maintaining the hash space (this information is obtained based on the routing 
table). Once the results are obtained, the documents are ranked and displayed. If the user wants more results, 
the request is again sent to the responding peer who will forward the result to its neighbours based on ‘=>’ 
relationship.
When the peer wants to logout, a ‘leave’ request is initiated. When one of its neighbours receive this ‘leave’ 
request the hash space of the leaving peer is merged with the neighbour and files are now managed by this 
peer.
Thread
Management Box
P
E
E
R
S
User 
Database
Communication 
Handler
Control
LayerDISTRIBUTED ARCHITECTURE
Figure 3: Overlay distributed network
Screenshots of GUI
Connect Screen
Login Screen
Boot Strap 
Server
New 
Peer
Peer
Peer
Login
Peer
JOIN
Peer
Peer
LEAVERegister Screen
Search Results pagePAGE RANK ALGORITM
Document ranking feature is added to the P2P system. . In our current system, the assumption is that all files 
are text files with no  relation to other documents. The number of times a file is downloaded is a direct 
reflection of the importance of the file. Thus the number of times the file is downloaded is considered as major 
metric in ranking the documents Match Coefficient is defined as the percentage of set bits in the search query 
that match the file digest. This parameter is given 90% of weightage in the document rank. 10% weightage is 
given to the number of downloads. Thus the Rank Factor is obtained using the following expression
Rank Factor = 0.9*Match Coefficient + 0.1*(No. of Downloads/100)
This rank factor is calculated for the search results and the results are sorted in descending order of this 
parameter. As extension, number of hits and peer feedback can also be included to obtain the rank factor.
In Centralised architecture, along with each file, a parameter is stored which is updated whenever the file is 
downloaded. When the file is first published the file is stored with the download parameter set to zero. 
Whenever the file is downloaded by any of the peers, an update message is sent to the Boot strap server 
which will increment this parameter. The state of this parameter is maintained. When a search query is 
received, the rank factor is calculated for the valid documents, sorted and sent to the peer.
In Distributed architecture, the approach is similar. All the peers managing the hash space will maintain the 
download parameter with each file. When a search query is received, rank factor is calculated for the valid 
documents and this parameter is sent along with the results to the peer (which requested the query). The end 
peer may receive search results from various peers. Once all these requests are received the end peer will now 
sort the results based on rank factor and display them to the user. Thus, the major difference in both the 
architecture is that the calculation of rank factor and sorting results takes place with the boot strap server in 
Centralised but in Distributed rank factor is sent along with the results by peers managing the hash space and 
the end peer will sort them. 
											HOW REQUESTS ARE PROCESSED
CENTRALISED
? Connect request
Whenever a user enters a boot strap IP address and clicks on connect button, a TCP connection is opened with 
the bootstrap server. A local validation is done in the GUI if the IP address entered is valid.
? Register request
When Register button is clicked, the GUI checks if all the fields have data entered then creates a Register 
object. This object is taken by the communication layer and XML is generated with password encrypted and 
sent to the boot strap server. If some of the fields are missing a pop up window appears asking the user to 
enter the data in appropriate field. When the server receives this XML message, the communication layer will 
first convert this message into an object and passes it the control layer. In this layer, it is checked if the 
username already exists. If yes, exception is thrown and handled by GUI, else the user details are added to the 
database and a success message is sent. When this message is received the GUI moves to the Login screen.
? Login Request
When login button is clicked, the GUI creates a login object, which is converted to an XML  with password 
encrypted  by the communication layer and sent to the server. The server again gets the object from its 
communication layer. It then searches for the username in the database and sees if the password provided 
matches the one in the database. If yes, a success is sent and Search page is displayed. If no, an error message 
is shown to the user
? Publish Request
When the user successfully logs in, publish request is triggered. In this request, all the text files in the “publish” 
folder are trigram hashed to obtain the file digest. Then a Publish XML request is created with file name, file 
size, file abstract, IP address and file digest information for every file. This request is sent to the server. The 
server then parses this information and adds the new files to its repository with download parameter 0. An 
existing file is not changed (to maintain the download parameter)? Search Request
When a search string is entered, the string is hashed to obtain the digest. This digest is sent to the server in an 
XML message. The server when it received this object from its communication layer will match the percentage 
of set bits in the query digest with the file digests. Based on the match parameter results are collected. Rank 
factor is calculated as explained the page rank algorithm and the results are sorted. Then an XML message is 
created with these results and sent back to the peer where the results are displayed in the GUI
? Download request
When the user clicks on the download button the search results page, the peer sends a File request to the IP 
address of the peer containing the file. The file is then sent to the peer directly and an update message is sent 
to the server. The server will increment the download parameter for this file (useful in page ranking)
? Change Password request
The user can change the password when he is logged in. When this request is triggered, both the old and new 
passwords are encrypted and sent in the XML message. The server decrypts then and matches the old 
password with the existing password in the database. If they match the new password is updated and else an 
error message is sent. Based on this the result is displayed accordingly.
? Forgot Password request
If the user clicks on this button, the username is sent in the XML message. The server checks if the username 
exists in the database and then generates a random password and mails it to the email ID the username is 
associated with.
? Logout Request
When a user clicks on logout request, the username is sent to the server which will log the user out and sends 
a success message
In the centralised model, the requests except the download request are sent to the boot strap server. So once 
the user logs in TCP session is established and tore down when the user leaves the system. The GUI layer 
creates the requests to be converted to XML by the communication layer. At the server, threads are spawned 
to handle multiple concurrent requests.
DISTRIBUTED

In the distributed model, requests are processed differently though the functionality is similar. The major 
change is that after every request sent the TCP connection is closed down. This is because the peer receiving 
the request may not be the peer which handles it and sends back the response. So this modification is made to 
the architecture. Every peer acts as both server and client. If a request is sent from one of the peer (client) the 
request thread waits in sleep state till the response is received and when a response message with the same 
message id as request is received the thread executes till completion. The server part of the peer will keep 
receiving requests from other peers and processes them in parallel. The details how requests are handled are 
mentioned below
? Connect request
Handled similar to the Centralised case, after successfully connecting login screen is shown and the TCP 
connection is closed to the server
? Register request
When Register button is clicked, the GUI checks if all the fields have data entered then passes the parameters 
to the Control layer where request is created. This object is taken by the communication layer and XML is 
generated with password encrypted and sent to the boot strap server. The server processes the request and 
sends the response back on a different port to the peer. The peer request thread waits till the response is 
received and then executes the response action.
? Login Request
When login button is clicked, the GUI again passes the parameters to the Control layer which creates a login 
object, which is converted to an XML with password encrypted by the communication layer and sent to the 
server. The server again gets the object from its communication layer. It then searches for the username in the 
database and sees if the password provided matches the one in the database. If yes, a success is sent and 
Search page is displayed. If no, an error message is shown to the user.If the login is success, an IP address is sent back in the message. This IP address indicates which peer to contact 
for the hash space management. The peer once it receives the login request creates a new Join request and 
sends it to the IP address sent by the boot strap server and closes the TCP connection.
The peer which receives the Join request will divide its hash space in any of the direction, gets the files which 
belong to this hash space and create a login response including this information along with its routing table 
(contains the information about the other peers) and sends it to the new peer.
Once the login response is received for the request, the peer will update its own new zone coordinates, saves 
the files in the repository and routing table. Later the publish request is triggered for seamless transfer of files.
? Publish Request
When the user successfully logs in, publish request is triggered. In this request, all the text files in the “publish” 
folder are trigram hashed to obtain the file digest. Individual publish requests are created for each file as there 
are a set of peers handling the hash space. For each publish request, the peer checks it routing table to see if 
its the peer managing the hash space the file is in. If yes, updates this entry in its files repository else it will 
send the request to the next peer in that direction. The request is forwarded till it reaches the peer managing 
the hash space the file belongs to. The receiving peer will update its database and send a success message. 
? Search Request
When a search string is entered, the string is hashed to obtain the digest. This digest is checked to verify if its 
handles by the same peer. If yes the digest is matched against the file digests in the database and the results 
are collected, sorted as per the rank factor and displayed to the user else the request is sent to the next peer 
based on the routing table. The next peer will either handle the request or forward it based on the hash space 
managed by it. Finally when an appropriate peer receives the request it will get the results and send them back 
directly to the first peer that created the search request. Based on the results received from various peers the 
end peer will sort them according to the rank factor and display them to the user. 
? Logout Request
When a user clicks on logout request, the username is sent to the server which will log the user out and sends 
success message. When this message is received a Leave request is generated consisting of zone coordinates, 
file handled by this peer and its current routing table. The request is sent to the neighbouring peers. One of 
the valid peers will merge this hash space with itself and add the files to its repository and updates its routing 
table and notifies the peers. Thus the system is not dependent on a single entity (like boot strap server in 
centralised case). Redundancy and scalability can be easily achieved with this model.
? Download request, Change Password request, Forgot Password request
Implementation is similar to centralised case.

TEST RESULTS

All the features (connect, login, register, publish, search, change / forgot password, logout) for both the 
architectures are tested and results are obtained as expected
It is observed that there is a trade off between the size of the digest and the performance of the system. If the 
digest size is too large the time taken for the search will be higher but the search results are obtained with 
greater accuracy and vice versa. Choosing a good digest length is critical for the overall system performance. 
It is observed that the Publish response takes similar time both the models. Initially based on the files present 
in the publish folder it takes some time for the files to be published. 
CENTRALISED TEXT SEARCH
In the P2P system implemented, the  search match parameter is configurable parameter. Varying this 
parameter we can get the results that match the search string from decent percentage to 100%. In Centralised, 
we varied this query match parameter from 0.90 to 0.99. It is observed that as we approached 0.99, the false 
positives decreased to a very low number.When tested with 27 text files for the match parameter set as 0.90
Search Query Total Results Total expected 
Results
False positives False Negatives
Not 8 8 0 0
Common 3 3 0 0
Amazing 1 1 0 0
Good 2 2 0 0
Slkfhslkfhlfkhweps 0 0 0 0
Ftp 1 1 0 0
Far far away 0 0 0 0
Interesting 1 1 0 0
Harry 4 4 0 0
Harry Chamber 1 1 0 0
Test 8 4 4 0
Stone 2 1 1 0
This is not a test 3 2 1 0
This is a test 4 3 1 0
Each task identify a 
member who will 
be the manager 
responsible
for its completion
2 1 1 0
When tested with 27 text files for the match parameter set as 0.99
Search Query Total Results Total expected 
Results
False positives False Negatives
Not 8 8 0 0
Common 3 3 0 0
Amazing 1 1 0 0
Good 2 2 0 0
Slkfhslkfhlfkhweps 0 0 0 0
Ftp 1 1 0 0
Far far away 0 0 0 0
Interesting 1 1 0 0
Harry 4 4 0 0
Harry Chamber 1 1 0 0
This is a test 4 3 1 0
Stone 2 1 1 0
Test 6 4 2 0
This is not a test 2 2 0 0
Each task identify a 
member who will 
be the manager 
responsible
1 1 0 0
It is observed that one of the files contained MATLAB code and was matching the word ‘test’ based on trigram 
search due to which false positives are obtained. Based on these tests, it is decided to use 0.99 as match 
parameter.DISTRIBUTED TEXT SEARCH
The search results obtained are similar to the centralised model when only one peer is handling all the files 
and the peer connected to it has a hash space but no files.
When tested with 27 text files for the match parameter set as 0.90
Search Query Total Results Total expected 
Results
False positives False Negatives
Not 8 8 0 0
Common 3 2 0 1
Amazing 1 1 0 0
Good 2 2 0 0
Slkfhslkfhlfkhweps 0 0 0 0
Ftp 1 1 0 0
Far far away 0 0 0 0
Interesting 1 1 0 0
Harry 4 4 0 0
Harry Chamber 1 1 0 0
Test 9 4 4 1
Stone 2 1 1 0
This is not a test 3 2 1 0
This is a test 4 3 1 0
Each task identify a 
member who will 
be the manager 
responsible
for its completion
2 1 1 0
When tested with 34 text files with 4 peers and for the match parameter set as 0.99
Search Query Total Results Total  expected 
Results
False positives False Negatives
Not 6 6 0 0
Common 2 2 0 0
Ftp 1 1 0 0
Far far away 0 0 0 0
Interesting 1 1 0 0
Harry 4 4 0 0
Harry Chamber 1 1 0 0
This is a test 4 3 1 0
Stone 2 1 1 0
Test 6 4 2 0
This is not a test 2 2 0 0
Each task identify a 
member who will 
be the manager 
responsible
1 1 0 0
Satellite 1 1 0 0
It is observed that when large number of results are obtained it takes more time to process and display them 
in both the models. The popular and not so popular queries are taking similar times. Overall, the search in 
distributed is faster as results from single peer are first displayed. Future Work
? The Page rank algorithm can be improved and with slight modifications we can extend the rank factor 
to consider the number of search hits for the document. The users can be provided with option to 
give feedback (both positive and negative) and thereby increase / decrease the rank of the document.
? The search can be extended for the numbers in search string as well. Currently if numbers are given 
the search is not working as expected. So the trigram algorithm can be extended and numbers can be 
considered.
? When the search results are displayed the text matching the query can be highlighted for user 
convenience.
? The system can be easily adapted to manage not only text files but any data (like .jpg, .mp3 etc)
? The system can be tested in scaled scenarios with larger number of peers to check the efficiency of 
both centralised and distributed implementations in such scenarios.
Conclusion
Thus, peer to peer system model is implemented in both centralised and distributed architecture. The 
architectures are tested and the results are presented. It is observed that the distributed system is  easily 
scalable and redundant. The centralised system is more efficient in searches but has a single point of failure.
*Note: We arrived at two names for the system
EAGLE EYE – Eagle is known to see clearly in a large space analogous to large file storage capacity and good 
search results identification
DTEXTR – Distributed Text search
