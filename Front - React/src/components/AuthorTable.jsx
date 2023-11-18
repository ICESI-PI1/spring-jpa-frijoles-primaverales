import {
  TableContainer,
  TableRow,
  TableHead,
  Table,
  TableCell,
  TableBody,
  Paper,
} from "@mui/material";
import PropTypes from "prop-types";
import AuthorRow from "./AuthorRow";

function AuthorTable({ authorList, delAuthor, editAuthor }) {
  const renderAuthor = () => {
    return authorList.map((author) => (
      <AuthorRow
        key={author.id}
        author={author}
        delAuthor={delAuthor}
        editAuthor={editAuthor}
      />
    ));
  };
  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell align="right">Id</TableCell>
            <TableCell align="left">Name</TableCell>
            <TableCell align="left">Nationality</TableCell>
            <TableCell align="left">Actions</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>{renderAuthor()}</TableBody>
      </Table>
    </TableContainer>
  );
}

AuthorTable.propTypes = {
  authorList: PropTypes.array,
  delAuthor: PropTypes.func,
  editAuthor: PropTypes.func,
};

export default AuthorTable;
