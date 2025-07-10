CREATE TABLE roles(
	id bigserial PRIMARY KEY,
	name varchar(45) NOT NULL,
	description varchar(45)
);

CREATE TABLE phones(
	id bigserial PRIMARY KEY,
	phone_number varchar(15) NOT NULL
);

CREATE OR REPLACE FUNCTION trigger_set_timestamp()
RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_at = NOW();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;



CREATE TABLE users(
	id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
	first_name varchar(30) NOT NULL,
	last_name varchar(30) NOT NULL,
	cpf varchar(14) NOT NULL UNIQUE,
	email varchar(100) NOT NULL UNIQUE,
	password varchar(100) NOT NULL,
	phones_id bigint NOT NULL,
	active boolean NOT NULL,
	created_at timestamp DEFAULT current_timestamp,
	updated_at timestamp DEFAULT current_timestamp,
	CONSTRAINT fk_users_phones
		FOREIGN KEY (phones_id)
		REFERENCES phones(id)
);

CREATE TRIGGER set_timestamp
BEFORE UPDATE ON users
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();

CREATE TABLE users_roles(
	id bigserial PRIMARY KEY,
	users_id uuid NOT NULL,
	roles_id bigint NOT NULL,
	CONSTRAINT fk_users_roles_users
		FOREIGN KEY (users_id)
		REFERENCES users(id),
	CONSTRAINT fk_users_roles_roles
		FOREIGN KEY (roles_id)
		REFERENCES roles(id)
);

CREATE TABLE addresses(
	id bigserial PRIMARY KEY,
	name varchar(50) NOT NULL,
	cep varchar(10) NOT NULL,
	neighborhood varchar(50) NOT NULL,
	street varchar(50) NOT NULL,
	number_house varchar(15) NOT NULL,
	complement varchar(200)
); 

CREATE TABLE users_addresses(
	id bigserial PRIMARY KEY,
	users_id uuidd NOT NULL,
	addresses_id bigint NOT NULL,
	CONSTRAINT fk_users_addresses_users
		FOREIGN KEY (users_id)
		REFERENCES users(id),
	CONSTRAINT fk_users_addresses_adresses
		FOREIGN KEY (addresses_id)
		REFERENCES addresses(id)
);

CREATE TABLE pizza_flavors(
	id bigserial PRIMARY KEY,
	name varchar(45) NOT NULL,
	description varchar(45),
	base_price decimal(10, 4) NOT NULL,
	available boolean NOT NULL
);

CREATE TABLE pizza_sizes(
	id bigserial PRIMARY KEY,
	name varchar(30) NOT NULL,
	coefficient decimal(10, 2) NOT NULL,
	flavor_quantity integer NOT NULL
);

CREATE TABLE extras(
	id bigserial PRIMARY KEY,
	name varchar(30) NOT NULL,
	price decimal(10, 2) NOT NULL,
	available boolean NOT NULL
);

CREATE TYPE valid_status AS ENUM ('pendente', 'em preparo', 'saiu pra entrega', 'entregue');

CREATE TABLE orders(
	id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
	users_id uuid NOT NULL,
	addresses_id bigint NOT NULL,
	note varchar(200),
	total_value decimal (10, 2),
	status valid_status NOT NULL,
	created_at timestamp DEFAULT current_timestamp,
	CONSTRAINT fk_orders_users
		FOREIGN KEY (users_id) 
		REFERENCES users(id),
	CONSTRAINT fk_orders_addresses
		FOREIGN KEY (addresses_id)
		REFERENCES addresses(id)
);

CREATE TABLE order_items(
	id bigserial PRIMARY KEY,
	orders_id uuid NOT NULL,
	pizza_sizes_id bigint NOT NULL,
	total_value decimal(10, 2) NOT NULL,
	CONSTRAINT fk_order_items_orders
		FOREIGN KEY (orders_id)
		REFERENCES orders(id),
	CONSTRAINT fk_order_items_pizza_sizes
		FOREIGN KEY (pizza_sizes_id)
		REFERENCES pizza_sizes(id)
);

CREATE TABLE order_items_pizza_flavors(
	id bigserial PRIMARY KEY,
	order_items_id bigint NOT NULL,
	pizza_flavors_id bigint NOT NULL,
	quantity integer NOT NULL,
	total_value decimal(10, 2) NOT NULL,
	CONSTRAINT fk_order_items_pizza_flavors_order_items
		FOREIGN KEY (order_items_id)
		REFERENCES order_items(id),
	CONSTRAINT fk_order_items_pizza_flavors_pizza_flavors
		FOREIGN KEY (pizza_flavors_id)
		REFERENCES pizza_flavors(id)
);

CREATE TABLE order_items_extras(
	id bigserial PRIMARY KEY,
	order_items_id bigint NOT NULL,
	extras_id bigint NOT NULL,
	quantity integer NOT NULL,
	total_value decimal(10, 2) NOT NULL
);