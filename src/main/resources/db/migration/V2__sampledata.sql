INSERT INTO account_type (name, created_date, updated_date, deleted_flag)
VALUES
  ('Single Tenant', NOW(), NOW(), FALSE),
  ('Multi Tenant', NOW(), NOW(), FALSE),
  ('Personal Microsoft Account', NOW(), NOW(), FALSE);

INSERT INTO platform (name, created_date, updated_date, deleted_flag)
VALUES
  ('Web', NOW(), NOW(), FALSE),
  ('Mobile', NOW(), NOW(), FALSE),
  ('Desktop', NOW(), NOW(), FALSE);