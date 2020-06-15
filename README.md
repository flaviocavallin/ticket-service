<h1>Ticket Service</h1>

<h4>1. To run the application execute</h4>
<p>./ticket-service.sh</p>

<h4>2. To run the application for development</h4>
<h5>Start MySQL on your local:</h5>
docker run -d -p 33060:3306 --name mysql -e MYSQL_ROOT_PASSWORD=root mysql:latest

<h4>3. If you have Postman</h4>
<p>Import into postman the collection ticket-service.postman_collection.json</p>

<h4>4. Use swagger</h4>
<p>http://localhost:8080/ticket-service/swagger-ui.html</p>