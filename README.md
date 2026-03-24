# Recruitment System API

RESTful API for managing candidates, companies, and job postings.

## Table of Contents

- [Overview](#overview)
- [Authentication](#authentication)
- [Resources and Endpoints](#resources-and-endpoints)
  - [Candidate](#candidate)
  - [Company](#company)
  - [Jobs](#jobs)
- [DTOs](#dtos)
- [Error Responses](#error-responses)

---

## Overview

This API allows you to:

- Register and authenticate candidates and companies
- Create job postings by companies
- Retrieve candidate profiles

Authentication is based on JWT (Bearer Token).

---

## Authentication

- **Candidate:**
  - `POST /candidate/auth` — Returns JWT token for candidates
- **Company:**
  - `POST /auth/company` — Returns JWT token for companies

Include the JWT token in the header `Authorization: Bearer <token>` to access protected endpoints.

---

## Resources and Endpoints

### Candidate

- `POST /candidate/` — Create a new candidate
  - Body: `{ username, name, email, password, description }`
- `POST /candidate/auth` — Authenticate candidate
  - Body: `{ username, password }`
  - Response: `{ access_token, expires_in }`
- `GET /candidate/` — Get the authenticated candidate's profile
  - Header: `Authorization: Bearer <token>`
  - Response: `{ id, username, email, name, description }`

### Company

- `POST /company/` — Create a new company
  - Body: `{ username, password, email, name, website, description }`
- `POST /auth/company` — Authenticate company
  - Body: `{ username, password }`
  - Response: `{ access_token, expires_in }`

### Jobs

- `POST /company/job/` — Create a job posting (authenticated company)
  - Header: `Authorization: Bearer <token>`
  - Body: `{ description, benefits, level }`
  - Response: `{ id, description, benefits, level, companyId, createdAt }`

---

## DTOs

### AuthCandidateRequestDTO

```json
{
  "username": "string",
  "password": "string"
}
```

### AuthCandidateResponseDTO / AuthCompanyResponseDTO

```json
{
  "access_token": "string",
  "expires_in": 1234567890
}
```

### CreateJobDTO

```json
{
  "description": "string",
  "benefits": "string",
  "level": "string"
}
```

---

## Error Responses

- 400: Invalid data or user already exists
- 401: Unauthorized (invalid token or incorrect credentials)
- 500: Internal server error

---

## Notes

- To create jobs, the company must be authenticated.
- To access the candidate profile, the candidate must be authenticated.
- The `access_token` field must be used as a Bearer Token in protected endpoints.

---

## Usage Examples

### Authenticate Candidate

```http
POST /candidate/auth
Content-Type: application/json
{
  "username": "welesonbatista",
  "password": "yourPassword"
}
```

### Authenticate Company

```http
POST /auth/company
Content-Type: application/json
{
  "username": "java company",
  "password": "yourPassword"
}
```

### Create Job

```http
POST /company/job/
Authorization: Bearer <token>
Content-Type: application/json
{
  "description": "job for java developer",
  "benefits": "Gympass, day off, ...",
  "level": "SENIOR"
}
```

---

## Contact

Questions or suggestions: open an issue or contact the project maintainer.
