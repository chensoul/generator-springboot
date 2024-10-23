create sequence IF NOT EXISTS <%= tableName %>_seq start with 1 increment by 50;

create tableIF NOT EXISTS  <%= tableName %> (
    <%_ if (databaseType != 'mariadb') { _%>
    id bigint DEFAULT nextval('<%= tableName %>_seq') not null,
    <%_ } _%>
    <%_ if (databaseType === 'mariadb') { _%>
    id bigint DEFAULT nextval(`<%= tableName %>_seq`) not null,
    <%_ } _%>
    <%_ if (databaseType != 'postgresql') { _%>
    text varchar(1024) not null,
    <%_ } _%>
    <%_ if (databaseType === 'postgresql') { _%>
    text text not null,
    <%_ } _%>
    primary key (id)
);
