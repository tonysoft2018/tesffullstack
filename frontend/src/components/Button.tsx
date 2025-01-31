import styled from "styled-components";

export const Button = styled.button<{ $bgColor?: string }>`
    border: 1px solid transparent;
    font-size: 1em;
    padding: 0.5rem 1.5rem;
    background: ${({ $bgColor }) => $bgColor || "#2b1edd"};
    border-radius: 8px;
    cursor: pointer;
    font-weight: 500;
    font-family: inherit;
    transition: border-color 0.25s;
    &:hover {
        border-color: #646cff;
    }

    &:focus,
    &:focus-visible {
        outline: 4px auto -webkit-focus-ring-color;
    }
`