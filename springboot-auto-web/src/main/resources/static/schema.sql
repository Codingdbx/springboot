drop table if exists PageSet;

/*==============================================================*/
/* Table: PageSet                                               */
/*==============================================================*/
create table PageSet
(
    page_id              varchar         primary key    not null,
    page_name            varchar                        null,
    page_url             varchar                        null,
    wait_time            varchar                        null,
    sort                 varchar                        null
);

drop table if exists ElementSet;

/*==============================================================*/
/* Table: ElementSet                                             */
/*==============================================================*/
create table ElementSet 
(
   element_id           varchar     primary key        not null,
   element_name         varchar                        null,
   element_type         varchar                        null,
   page_id              varchar                        null,
   iframe_id            varchar                        null,
   location_type        varchar                        null,
   location_value       varchar                        null,
   data_id              varchar                        null,
   sort                 varchar                        null
);


drop table if exists IframeSet;

/*==============================================================*/
/* Table: IframeSetServiceImpl                                             */
/*==============================================================*/
create table IframeSet
(
    iframe_id            varchar        primary key     not null,
    location_type        varchar                        null,
    location_value       varchar                        null
);


drop table if exists DataSet;

/*==============================================================*/
/* Table: DataSet                                               */
/*==============================================================*/
create table DataSet
(
    data_id              varchar      primary key       not null,
    source_id            varchar                        null,
    data_value           varchar                        null,
    excel_sheet_num      varchar                        null,
    excel_sheet_name     varchar                        null,
    excel_row            varchar                        null,
    excel_cell           varchar                        null,
    word_table_num       varchar                        null,
    word_table_row       varchar                        null,
    word_table_cell      varchar                        null,
    word_paragraph_num   varchar                        null,
    word_run_num         varchar                        null,
    word_book_mark       varchar                        null
);


drop table if exists DataSourceSet;

/*==============================================================*/
/* Table: DataSourceSet                                         */
/*==============================================================*/
create table DataSourceSet
(
    source_id            varchar         primary key    null,
    source_url           varchar                        null,
    source_type          varchar                        null
);
