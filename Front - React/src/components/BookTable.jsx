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
import BookRow from "./BookRow";

function BookTable({ bookList, delBook, editBook }) {
  const renderBook = () => {
    return bookList.map((book) => (
      <BookRow
        key={book.id}
        book={book}
        delBook={delBook}
        editBook={editBook}
      />
    ));
  };
  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell align="right">Id</TableCell>
            <TableCell align="left">Title</TableCell>
            <TableCell align="left">Author</TableCell>
            <TableCell align="left">Actions</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>{renderBook()}</TableBody>
      </Table>
    </TableContainer>
  );
}

BookTable.propTypes = {
  bookList: PropTypes.array,
  delBook: PropTypes.func,
  editBook: PropTypes.func,
};

export default BookTable;
