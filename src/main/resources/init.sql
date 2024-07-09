INSERT INTO term_history (
    term_type,
    revision_date,
    is_essential,
    is_latest_revision,
    created_at,
    updated_at
) VALUES (
            'ESSENTIAL_IS_OVER_FOURTEEN',
             now(),
             true,
             true,
             now(),
             now()
         );

INSERT INTO term_history (
    term_type,
    revision_date,
    is_essential,
    is_latest_revision,
    created_at,
    updated_at
) VALUES (
             'ESSENTIAL_TERMS_OF_SERVICE',
             now(),
             true,
             true,
             now(),
             now()
         );

INSERT INTO term_history (
    term_type,
    revision_date,
    is_essential,
    is_latest_revision,
    created_at,
    updated_at
) VALUES (
             'ESSENTIAL_PERSONAL_INFO_COLLECT',
             now(),
             true,
             true,
             now(),
             now()
         );
INSERT INTO term_history (
    term_type,
    revision_date,
    is_essential,
    is_latest_revision,
    created_at,
    updated_at
) VALUES (
             'OPTIONAL_PERSONAL_INFO_MARKETING',
             now(),
             false,
             true,
             now(),
             now()
         );
INSERT INTO term_history (
    term_type,
    revision_date,
    is_essential,
    is_latest_revision,
    created_at,
    updated_at
) VALUES (
             'OPTIONAL_THIRD_PARTY_SHARE',
             now(),
             false,
             true,
             now(),
             now()
         );