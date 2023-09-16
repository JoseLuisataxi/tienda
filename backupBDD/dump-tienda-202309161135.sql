--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

-- Started on 2023-09-16 11:35:20

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4895 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 16400)
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    id_cliente bigint NOT NULL,
    identificacion character varying(255) NOT NULL,
    nombres character varying(255) NOT NULL
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16399)
-- Name: cliente_id_cliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_id_cliente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cliente_id_cliente_seq OWNER TO postgres;

--
-- TOC entry 4896 (class 0 OID 0)
-- Dependencies: 215
-- Name: cliente_id_cliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_id_cliente_seq OWNED BY public.cliente.id_cliente;


--
-- TOC entry 226 (class 1259 OID 16441)
-- Name: detalle_pedido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.detalle_pedido (
    id_detalle_pedido bigint NOT NULL,
    cantidad numeric NOT NULL,
    id_tienda_producto bigint NOT NULL,
    id_pedido integer
);


ALTER TABLE public.detalle_pedido OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16440)
-- Name: detalle_pedido_id_detalle_pedido_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.detalle_pedido_id_detalle_pedido_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.detalle_pedido_id_detalle_pedido_seq OWNER TO postgres;

--
-- TOC entry 4897 (class 0 OID 0)
-- Dependencies: 225
-- Name: detalle_pedido_id_detalle_pedido_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.detalle_pedido_id_detalle_pedido_seq OWNED BY public.detalle_pedido.id_detalle_pedido;


--
-- TOC entry 224 (class 1259 OID 16434)
-- Name: pedido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pedido (
    id_pedido bigint NOT NULL,
    fecha timestamp(6) without time zone NOT NULL,
    id_cliente bigint
);


ALTER TABLE public.pedido OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16433)
-- Name: pedido_id_pedido_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pedido_id_pedido_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pedido_id_pedido_seq OWNER TO postgres;

--
-- TOC entry 4898 (class 0 OID 0)
-- Dependencies: 223
-- Name: pedido_id_pedido_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pedido_id_pedido_seq OWNED BY public.pedido.id_pedido;


--
-- TOC entry 220 (class 1259 OID 16418)
-- Name: producto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.producto (
    id_producto bigint NOT NULL,
    nombre character varying(255) NOT NULL
);


ALTER TABLE public.producto OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16417)
-- Name: producto_id_producto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.producto_id_producto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.producto_id_producto_seq OWNER TO postgres;

--
-- TOC entry 4899 (class 0 OID 0)
-- Dependencies: 219
-- Name: producto_id_producto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.producto_id_producto_seq OWNED BY public.producto.id_producto;


--
-- TOC entry 218 (class 1259 OID 16409)
-- Name: tienda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tienda (
    id_tienda bigint NOT NULL,
    nombre character varying(255) NOT NULL
);


ALTER TABLE public.tienda OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16408)
-- Name: tienda_id_tienda_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tienda_id_tienda_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tienda_id_tienda_seq OWNER TO postgres;

--
-- TOC entry 4900 (class 0 OID 0)
-- Dependencies: 217
-- Name: tienda_id_tienda_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tienda_id_tienda_seq OWNED BY public.tienda.id_tienda;


--
-- TOC entry 222 (class 1259 OID 16427)
-- Name: tienda_producto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tienda_producto (
    id_tienda_producto bigint NOT NULL,
    id_tienda bigint,
    id_producto integer,
    stock numeric(38,2)
);


ALTER TABLE public.tienda_producto OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16426)
-- Name: tienda_producto_id_tienda_producto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tienda_producto_id_tienda_producto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tienda_producto_id_tienda_producto_seq OWNER TO postgres;

--
-- TOC entry 4901 (class 0 OID 0)
-- Dependencies: 221
-- Name: tienda_producto_id_tienda_producto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tienda_producto_id_tienda_producto_seq OWNED BY public.tienda_producto.id_tienda_producto;


--
-- TOC entry 4713 (class 2604 OID 16468)
-- Name: cliente id_cliente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente ALTER COLUMN id_cliente SET DEFAULT nextval('public.cliente_id_cliente_seq'::regclass);


--
-- TOC entry 4718 (class 2604 OID 16494)
-- Name: detalle_pedido id_detalle_pedido; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_pedido ALTER COLUMN id_detalle_pedido SET DEFAULT nextval('public.detalle_pedido_id_detalle_pedido_seq'::regclass);


--
-- TOC entry 4717 (class 2604 OID 16509)
-- Name: pedido id_pedido; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido ALTER COLUMN id_pedido SET DEFAULT nextval('public.pedido_id_pedido_seq'::regclass);


--
-- TOC entry 4715 (class 2604 OID 16529)
-- Name: producto id_producto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.producto ALTER COLUMN id_producto SET DEFAULT nextval('public.producto_id_producto_seq'::regclass);


--
-- TOC entry 4714 (class 2604 OID 16547)
-- Name: tienda id_tienda; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tienda ALTER COLUMN id_tienda SET DEFAULT nextval('public.tienda_id_tienda_seq'::regclass);


--
-- TOC entry 4716 (class 2604 OID 16565)
-- Name: tienda_producto id_tienda_producto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tienda_producto ALTER COLUMN id_tienda_producto SET DEFAULT nextval('public.tienda_producto_id_tienda_producto_seq'::regclass);


--
-- TOC entry 4879 (class 0 OID 16400)
-- Dependencies: 216
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cliente (id_cliente, identificacion, nombres) FROM stdin;
1	0603981374	JOSE LUISATAXI
\.


--
-- TOC entry 4889 (class 0 OID 16441)
-- Dependencies: 226
-- Data for Name: detalle_pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.detalle_pedido (id_detalle_pedido, cantidad, id_tienda_producto, id_pedido) FROM stdin;
\.


--
-- TOC entry 4887 (class 0 OID 16434)
-- Dependencies: 224
-- Data for Name: pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pedido (id_pedido, fecha, id_cliente) FROM stdin;
\.


--
-- TOC entry 4883 (class 0 OID 16418)
-- Dependencies: 220
-- Data for Name: producto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.producto (id_producto, nombre) FROM stdin;
1	Producto1
\.


--
-- TOC entry 4881 (class 0 OID 16409)
-- Dependencies: 218
-- Data for Name: tienda; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tienda (id_tienda, nombre) FROM stdin;
1	Tienda1
\.


--
-- TOC entry 4885 (class 0 OID 16427)
-- Dependencies: 222
-- Data for Name: tienda_producto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tienda_producto (id_tienda_producto, id_tienda, id_producto, stock) FROM stdin;
1	1	1	30.00
\.


--
-- TOC entry 4902 (class 0 OID 0)
-- Dependencies: 215
-- Name: cliente_id_cliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cliente_id_cliente_seq', 1, true);


--
-- TOC entry 4903 (class 0 OID 0)
-- Dependencies: 225
-- Name: detalle_pedido_id_detalle_pedido_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.detalle_pedido_id_detalle_pedido_seq', 2, true);


--
-- TOC entry 4904 (class 0 OID 0)
-- Dependencies: 223
-- Name: pedido_id_pedido_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pedido_id_pedido_seq', 4, true);


--
-- TOC entry 4905 (class 0 OID 0)
-- Dependencies: 219
-- Name: producto_id_producto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.producto_id_producto_seq', 1, true);


--
-- TOC entry 4906 (class 0 OID 0)
-- Dependencies: 217
-- Name: tienda_id_tienda_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tienda_id_tienda_seq', 1, true);


--
-- TOC entry 4907 (class 0 OID 0)
-- Dependencies: 221
-- Name: tienda_producto_id_tienda_producto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tienda_producto_id_tienda_producto_seq', 1, true);


--
-- TOC entry 4720 (class 2606 OID 16470)
-- Name: cliente cliente_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pk PRIMARY KEY (id_cliente);


--
-- TOC entry 4730 (class 2606 OID 16496)
-- Name: detalle_pedido detalle_pedido_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_pedido
    ADD CONSTRAINT detalle_pedido_pk PRIMARY KEY (id_detalle_pedido);


--
-- TOC entry 4728 (class 2606 OID 16511)
-- Name: pedido pedido_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_pk PRIMARY KEY (id_pedido);


--
-- TOC entry 4724 (class 2606 OID 16531)
-- Name: producto producto_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.producto
    ADD CONSTRAINT producto_pk PRIMARY KEY (id_producto);


--
-- TOC entry 4722 (class 2606 OID 16549)
-- Name: tienda tienda_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tienda
    ADD CONSTRAINT tienda_pk PRIMARY KEY (id_tienda);


--
-- TOC entry 4726 (class 2606 OID 16567)
-- Name: tienda_producto tienda_producto_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tienda_producto
    ADD CONSTRAINT tienda_producto_pk PRIMARY KEY (id_tienda_producto);


--
-- TOC entry 4734 (class 2606 OID 16582)
-- Name: detalle_pedido detalle_pedido_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_pedido
    ADD CONSTRAINT detalle_pedido_fk FOREIGN KEY (id_pedido) REFERENCES public.pedido(id_pedido);


--
-- TOC entry 4733 (class 2606 OID 16520)
-- Name: pedido pedido_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_fk FOREIGN KEY (id_cliente) REFERENCES public.cliente(id_cliente);


--
-- TOC entry 4731 (class 2606 OID 16572)
-- Name: tienda_producto tienda_producto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tienda_producto
    ADD CONSTRAINT tienda_producto_fk FOREIGN KEY (id_tienda) REFERENCES public.tienda(id_tienda);


--
-- TOC entry 4732 (class 2606 OID 16532)
-- Name: tienda_producto tienda_producto_fk_1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tienda_producto
    ADD CONSTRAINT tienda_producto_fk_1 FOREIGN KEY (id_producto) REFERENCES public.producto(id_producto);


-- Completed on 2023-09-16 11:35:21

--
-- PostgreSQL database dump complete
--

