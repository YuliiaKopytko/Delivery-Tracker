CREATE TABLE IF NOT EXISTS orders (
                                      id VARCHAR(255) PRIMARY KEY,
                                      product VARCHAR(255) NOT NULL,
                                      quantity INTEGER NOT NULL,
                                      payment_status VARCHAR(50)
);
