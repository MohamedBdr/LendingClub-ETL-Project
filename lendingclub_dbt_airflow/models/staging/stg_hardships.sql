with raw_hardships as (
    select * from {{ source('raw_data', 'hardships') }}
)

select
    _AIRBYTE_RAW_ID,
    cast(_AIRBYTE_EXTRACTED_AT as timestamp) as extracted_at,
    _AIRBYTE_META,

    case when HARDSHIP_FLAG = 'Y' then true else false end as hardship_flag,
    nullif(HARDSHIP_TYPE, '') as hardship_type,
    cast(HARDSHIPS_KEY as int) as hardships_key,
    nullif(HARDSHIP_STATUS, '') as hardship_status,

    _AB_SOURCE_FILE_URL as source_file_url,
    case when DEBT_SETTLEMENT_FLAG = 'Y' then true else false end as debt_settlement_flag,
    cast(_AB_SOURCE_FILE_LAST_MODIFIED as timestamp) as source_file_last_modified

from raw_hardships
