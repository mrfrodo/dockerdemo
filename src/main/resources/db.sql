
create table if not exists customer (
  dd_id serial primary key,
  customer_id text,
  customer_name text,
  customer_type text,
  customer_owner text,
  customer_creationdate timestamptz,
  customer_updatedate timestamptz
);

drop table customer;

