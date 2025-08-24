with raw_loandetails as (
    select * from {{ source('raw_data', 'loandetails') }}
)

select
    _AIRBYTE_RAW_ID,
    cast(_AIRBYTE_EXTRACTED_AT as timestamp) as extracted_at,
    _AIRBYTE_META,

    nullif(TERM, '') as term,
    nullif(GRADE, '') as grade,
    nullif(TITLE, '') as title,
    nullif(PURPOSE, '') as purpose,
    nullif(SUB_GRADE, '') as sub_grade,
    nullif(APPLICATION_TYPE, '') as application_type,

    cast(LOAN_DETAILS_KEY as int) as loan_details_key,

    case 
        when INITIAL_LIST_STATUS = 'f' then 'finalized'
        when INITIAL_LIST_STATUS = 'w' then 'waiting'
        else INITIAL_LIST_STATUS
    end as initial_list_status

from raw_loandetails
