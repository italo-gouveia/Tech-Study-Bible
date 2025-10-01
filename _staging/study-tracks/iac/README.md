# Infrastructure as Code (Terraform, Ansible) - Study Track

## Objectives
- Provision and manage infrastructure declaratively
- Separate provisioning (Terraform) and configuration (Ansible)
- Apply modules, state management, and idempotent playbooks

## Study Path
1. Terraform Basics: providers, resources, modules, state
2. Workspaces & Backends: remote state, locking
3. Ansible Basics: inventory, playbooks, roles, handlers
4. Secrets: SSM/Secrets Manager/Vault
5. CICD: plan/apply gates, policy checks

## Hands-on
- Terraform: VPC + EC2 + SG minimal stack (notes)
- Ansible: configure service + systemd + templating
- Remote state backend (S3 + DynamoDB) writeup

## Integration Points
- Notes: `notes/iac/*`
- Projects: `projects/iac-labs/*`
