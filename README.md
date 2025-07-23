# Distributed Matrix Addition using Java RMI and Sockets

## Project Infos
This project demonstrates how to perform **matrix addition** using a combination of **Java RMI (Remote Method Invocation)** and **Sockets**. The matrix is divided into 4 submatrices, each handled by a separate slave process. The system consists of:

- `StartRegistry.java`: Launches the RMI registry.
- `Server.java`: Acts as the RMI server, splits the matrices and delegates submatrix addition to slaves.
- `Slave1.java` to `Slave4.java`: Perform addition of submatrices.
- `Client.java`: Collects matrix inputs and sends the request to the server.

---

## How It Works
1. **Start the RMI Registry** using `StartRegistry.java`.( In netbeans Click Run File )
2. **Launch the Server** (`Server.java`), which binds the remote object.( In netbeans Click Run File )
3. **Start Slave Processes**:
    - `Slave1.java` on port `5001` ( In netbeans Click Run File )
    - `Slave2.java` on port `5002` ( In netbeans Click Run File )
    - `Slave3.java` on port `5003` ( In netbeans Click Run File )
    - `Slave4.java` on port `5004` ( In netbeans Click Run File )
4. **Run the Client** (`Client.java`) to input two matrices and send them to the server. ( In netbeans Click Run File )

The server splits the matrices into 4 submatrices and sends each pair to a different slave. Each slave adds the submatrices and sends the result back. The server merges the results and returns the final matrix to the client.

---

## Project Structure
- Client.java
- Hello.java
- RmiImplHelloInterface.java
- Server.java
- StartRegistry.java
- Slave1.java
- Slave2.java
- Slave3.java
- Slave4.java

## Author 
Maroua Boumchich
