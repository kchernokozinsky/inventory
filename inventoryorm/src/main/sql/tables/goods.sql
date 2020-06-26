--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

-- Started on 2020-06-23 18:55:18

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 204 (class 1259 OID 24600)
-- Name: goods; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.goods (
    id integer NOT NULL,
    name character varying NOT NULL,
    number integer NOT NULL,
    group_id integer NOT NULL
);


ALTER TABLE public.goods OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 24628)
-- Name: Goods_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.goods ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Goods_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 10000
    CACHE 1
);


--
-- TOC entry 2832 (class 0 OID 24600)
-- Dependencies: 204
-- Data for Name: goods; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2839 (class 0 OID 0)
-- Dependencies: 206
-- Name: Goods_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Goods_id_seq"', 1, false);


--
-- TOC entry 2701 (class 2606 OID 24607)
-- Name: goods Goods_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.goods
    ADD CONSTRAINT "Goods_pkey" PRIMARY KEY (id);


--
-- TOC entry 2704 (class 2606 OID 24609)
-- Name: goods name_good; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.goods
    ADD CONSTRAINT name_good UNIQUE (name);


--
-- TOC entry 2702 (class 1259 OID 24627)
-- Name: fki_fk_goods_groups; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_fk_goods_groups ON public.goods USING btree (group_id);


--
-- TOC entry 2705 (class 2606 OID 24622)
-- Name: goods fk_goods_groups; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.goods
    ADD CONSTRAINT fk_goods_groups FOREIGN KEY (group_id) REFERENCES public.groups(id) NOT VALID;


-- Completed on 2020-06-23 18:55:18

--
-- PostgreSQL database dump complete
--

