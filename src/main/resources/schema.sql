

CREATE TABLE public.plots
(
    id                  int8            NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    name                varchar         NOT NULL,
    status              varchar         NOT NULL,
    creation_date       timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT plots_pk PRIMARY KEY (id)
);


CREATE TABLE public.time_slots
(
    id                      int8            NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    start_time              TIME            NOT NULL,
    duration_in_minutes     numeric         NOT NULL,
    water_amount            numeric         NOT NULL,

    CONSTRAINT time_slots_pk PRIMARY KEY (id),
    CONSTRAINT plot_fk FOREIGN KEY (id) REFERENCES plots(id)

);







