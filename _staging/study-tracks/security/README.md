# Security (OWASP, Auth) - Study Track

## Objectives
- Understand OWASP Top 10 and practical mitigations
- Implement authentication/authorization patterns
- Handle secrets, input validation, and secure storage

## Study Path
1. OWASP Top 10: XSS, SQLi, CSRF, SSRF, IDOR, etc.
2. Auth Basics: sessions, JWT, OAuth2/OIDC
3. Secrets & Config: env, vaults, rotation
4. Input Validation: whitelist, structured parsing
5. Secure Storage: hashing (bcrypt/argon2), encryption at rest/in transit

## Hands-on
- Build a minimal auth service (Go): signup/login, JWT, refresh
- Add input validation and secure headers
- Basic security testing checklist

## Integration Points
- Projects: `projects/go/security-labs/*`
- Notes: `notes/security/*` with OWASP cheat sheets
