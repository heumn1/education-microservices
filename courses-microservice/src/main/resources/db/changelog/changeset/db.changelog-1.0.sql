--changeset heumn:1

CREATE TABLE IF NOT EXISTS public.user
(
    id_user bigserial primary key,
    name_user varchar(100) not null,
    lastname_user varchar(100) not null,
    email_user varchar(150) not null,
    password_user varchar(100) not null,
    date_create_user timestamp not null,
    role_user varchar(100) not null
);

CREATE TABLE IF NOT EXISTS public.courses
(
    id_course bigserial primary key,
    name_course varchar(50) not null,
    description_course varchar(500),
    time_course varchar(150),
    price_course double precision not null,
    category_course varchar(50) not null,
    rating_course real not null
);

CREATE TABLE IF NOT EXISTS public.user_courses
(
    id_user bigint references public.user not null,
    id_course bigint references public.courses not null
);

CREATE TABLE IF NOT EXISTS public.step
(
    id_step bigserial primary key,
    name_step varchar(50) not null,
    file_step varchar(500),
    course_step bigint references public.courses not null
);

CREATE TABLE IF NOT EXISTS public.step_comments
(
    id_comment bigserial primary key,
    text_comment varchar(250) not null,
    step_comment bigint references public.step not null,
    user_comment bigint references public.user not null
);

