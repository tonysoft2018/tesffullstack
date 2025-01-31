import styled from "styled-components";

const Table = styled.table`
    width: 100%;
    color: #000;
    border-collapse: collapse;
    margin: 20px 0;
    font-size: 18px;
    text-align: left;
    background: #fff;
    border-radius: 8px;
    overflow: hidden;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    & th, td {
        padding: 0.5rem 1rem;
    }
`;

export default Table;