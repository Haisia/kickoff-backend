create extension if not exists "uuid-ossp";

DO '
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = ''league_type'') THEN
        CREATE TYPE league_type AS ENUM (''UNKNOWN'', ''CUP'', ''LEAGUE'');
    END IF;
END
';

DO '
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = ''url_type'') THEN
        CREATE TYPE url_type AS ENUM (''INNER'', ''EXTERNAL'');
    END IF;
END
';

create table if not exists public.leagues
(
  league_id              uuid not null primary key,
  api_football_league_id bigint,
  name                   varchar(255),
  country_name           varchar(255),
  country_code           varchar(255),
  flag_url               varchar(255),
  flag_url_type          url_type not null,
  type                   league_type not null,
  created_at             timestamp(6),
  updated_at             timestamp(6)
);

alter table public.leagues
  owner to root;

create table if not exists public.league_logos
(
  league_id uuid not null references public.leagues,
  url       varchar(255),
  url_type  url_type not null
);

alter table public.league_logos
  owner to root;

create table if not exists public.seasons
(
  season_id  uuid not null primary key,
  league_id  uuid not null references public.leagues,
  year       integer,
  start_date date,
  end_date   date,
  created_at timestamp(6),
  updated_at timestamp(6)
);

alter table public.seasons
  owner to root;

create table if not exists public.teams
(
  team_id              uuid not null primary key,
  api_football_team_id bigint unique,
  name                 varchar(255),
  code                 varchar(255),
  country              varchar(255),
  founded              integer,
  national             boolean,
  league_id            uuid references public.leagues,
  created_at           timestamp(6),
  updated_at           timestamp(6)
);

alter table public.teams
  owner to root;

create table if not exists public.team_logos
(
  team_id  uuid not null references public.teams,
  url      varchar(255),
  url_type url_type not null
);

alter table public.team_logos
  owner to root;


create table if not exists public.venues
(
  team_id               uuid not null references public.teams,
  api_football_venue_id bigint,
  name                  varchar(255),
  address               varchar(255),
  city                  varchar(255),
  capacity              bigint,
  surface               varchar(255),
  image                 varchar(255)
);

alter table public.venues
  owner to root;


create table if not exists public.season_map_team
(
  league_id  uuid not null references public.leagues,
  season_id  uuid not null references public.seasons,
  team_id    uuid references public.teams,
  created_at timestamp(6),
  updated_at timestamp(6)
);

alter table public.season_map_team
  owner to root;



