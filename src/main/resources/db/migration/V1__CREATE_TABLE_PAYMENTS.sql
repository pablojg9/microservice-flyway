CREATE TABLE payments (
    id bigint NOT NULL AUTO_INCREMENT,
    value_payment decimal(19,2) NOT NULL,
    name_payments varchar(100) DEFAULT NULL,
    number_payment varchar(19) DEFAULT NULL,
    expiration varchar(7) DEFAULT NULL,
    code_payment varchar(3) DEFAULT NULL,
    status varchar(255) NOT NULL,
    order_id bigint NOT NULL,
    form_payment_id bigint NOT NULL,
    PRIMARY KEY (id)
);