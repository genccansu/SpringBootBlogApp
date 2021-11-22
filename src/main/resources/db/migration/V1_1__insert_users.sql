INSERT INTO
    users( user_id,creation_date,display_name, email,gender,last_name,"name")
values ('12345',current_timestamp,'gncccansu','cansu123@gmail.com','FEMALE','genc','cansu')
ON CONFLICT DO NOTHING;