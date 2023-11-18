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
  
  function AuthorBooksTable({ bookList }) {
    const renderBook = () => {
        if (!bookList) {
            // lanzar mensaje de alerta 
            return null; // o un mensaje de carga, en caso necesario
          }
        return bookList.map((book) => (
            <TableRow
            key={book.id}
            sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
            <TableCell align="right">{book.id}</TableCell>
            <TableCell align="left">{book.title}</TableCell>
            <TableCell align="left">{book.author.name}</TableCell>
            </TableRow>
        ));
    };
    return (
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell align="right"><b>Id</b></TableCell>
              <TableCell align="left"><b>Title</b></TableCell>
              <TableCell align="left"><b>Author</b></TableCell>
            </TableRow>
          </TableHead>
          <TableBody>{renderBook()}</TableBody>
        </Table>
      </TableContainer>
    );
  }
  
  AuthorBooksTable.propTypes = {
    bookList: PropTypes.array,
  };
  
  export default AuthorBooksTable;
  