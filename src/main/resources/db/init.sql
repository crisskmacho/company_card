-- Table: public.products

-- DROP TABLE IF EXISTS public.products;

CREATE TABLE IF NOT EXISTS public.products
(
    product_id character varying(6) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    type character varying(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (product_id),
    CONSTRAINT products_type_check CHECK (type::text = ANY (ARRAY['DEBIT'::character varying, 'CREDIT'::character varying, 'PREPAID'::character varying]::text[]))
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.products
    OWNER to postgres;



-- Table: public.cards

-- DROP TABLE IF EXISTS public.cards;

CREATE TABLE IF NOT EXISTS public.cards
(
    card_id uuid NOT NULL,
    balance numeric(38,2) NOT NULL,
    blocked_at timestamp(6) with time zone,
                                blocked_reason character varying(255) COLLATE pg_catalog."default",
    card_number character varying(16) COLLATE pg_catalog."default" NOT NULL,
    currency character varying(3) COLLATE pg_catalog."default" NOT NULL,
    expires_at date NOT NULL,
    holder_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    status character varying(255) COLLATE pg_catalog."default" NOT NULL,
    product_id character varying(6) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT cards_pkey PRIMARY KEY (card_id),
    CONSTRAINT ukqualp9iflk959u561wanavuj1 UNIQUE (card_number),
    CONSTRAINT fkpyu5snyelx74iq561qs9x6feh FOREIGN KEY (product_id)
    REFERENCES public.products (product_id) MATCH SIMPLE
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION,
    CONSTRAINT cards_status_check CHECK (status::text = ANY (ARRAY['ISSUED'::character varying, 'ACTIVE'::character varying, 'BLOCKED'::character varying]::text[]))
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.cards
    OWNER to postgres;