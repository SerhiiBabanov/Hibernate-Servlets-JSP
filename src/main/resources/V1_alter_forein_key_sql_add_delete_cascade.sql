ALTER TABLE developer_skill_relation
    DROP CONSTRAINT developer_skill_relation_developer_id_fkey,
    ADD CONSTRAINT developer_skill_relation_developer_id_fkey
        FOREIGN KEY (developer_id)
            REFERENCES developers (id)
            ON DELETE CASCADE;
ALTER TABLE developer_skill_relation
    DROP CONSTRAINT developer_skill_relation_skill_id_fkey,
    ADD CONSTRAINT developer_skill_relation_skill_id_fkey
        FOREIGN KEY (skill_id)
            REFERENCES skills (id)
            ON DELETE CASCADE;
ALTER TABLE project_developer_relation
    DROP CONSTRAINT project_developer_relation_developer_id_fkey,
    ADD CONSTRAINT project_developer_relation_developer_id_fkey
        FOREIGN KEY (developer_id)
            REFERENCES developers (id)
            ON DELETE CASCADE;
ALTER TABLE project_developer_relation
    DROP CONSTRAINT project_developer_relation_project_id_fkey,
    ADD CONSTRAINT project_developer_relation_project_id_fkey
        FOREIGN KEY (project_id)
            REFERENCES projects(id)
            ON DELETE CASCADE;

