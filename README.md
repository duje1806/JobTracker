JobTracker Backend

REST API backend app za praćenje prijava za posao.
Korisnici se autentificiraju pomoću JWT-a.

Funkcionalnosti
- User registracija i login
- JWT autentifikacija
- CRUD operacije nad Job Applications
- Dodavanje bilješki (Notes) za svaku prijavu
- Svaki korisnik vidi samo svoje podatke
- Globalno rukovanje greškama (400 / 403 / 404)

- Autentifikacija
Aplikacija koristi JWT (JSON Web Token)

Register : 
POST /api/auth/register

{
  "email": "user@example.com",
  "password": "password123"
}

Login : 
POST /api/auth/login

{
  "token": "JWT_TOKEN"
}

JWT token se šalje u headeru za sve zaštićene rute

Create Job Application
POST /api/job-applications

{
"companyName": "Google",
"position": "Backend Developer",
"appliedDate": "2026-02-08",
"status": "APPLIED"
}
Get All Job Applications
GET /api/job-applications

Get Job Application by ID
GET /api/job-applications/{id}

Update Job Application
PUT /api/job-applications/{id}

Delete Job Application
DELETE /api/job-applications/{id}

Add Note to Job Application
POST /api/job-applications/{jobApplicationId}/notes

POKRETANJE APLIKACIJE
mvn clean install
mvn spring-boot:run

Aplikacija se pokreće na : http://localhost:8080
