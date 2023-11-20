import { useEffect, useState } from "react";
import PropTypes from "prop-types";
import { Box, Button, TextField } from "@mui/material";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";

function BookForm({ authorList, addBook, bookEdit }) {
  const [id, setId] = useState("");
  const [title, setTitle] = useState("");
  const [author, setAuthor] = useState("");
  const [publicationDate, setPublicationDate] = useState("");

  useEffect(() => {
    if (bookEdit) {
      setId(bookEdit.id || "");
      setTitle(bookEdit.title || "");
      setAuthor(bookEdit.author.id || "");

      const formattedDate = bookEdit.publicationDate
      ? new Date(bookEdit.publicationDate).toISOString().split('T')[0]
      : "";
      console.log(bookEdit.publicationDate);
      setPublicationDate(formattedDate);
    } else {
      setId("");
      setTitle("");
      setAuthor("");
      setPublicationDate("");
    }
  }, [bookEdit]);

  const handleClick = () => {
    addBook({ id, title, author, publicationDate });
  };

  const handleChange = (event) => {
    setAuthor(event.target.value);
  };

  const renderAuthors = () => {
    return authorList.map((author) => (
      <MenuItem key={author.id} value={author.id}>
        {author.name}
      </MenuItem>
    ));
  };

  return (
    <Box
      component="form"
      sx={{
        "& > :not(style)": { m: 1, width: "30ch" },
      }}
      noValidate
      autoComplete="off"
    >
      <TextField
        label="Title"
        variant="standard"
        value={title}
        onChange={(e) => {
          setTitle(e.target.value);
        }}
      />
      <InputLabel id="demo-simple-select-label">Author</InputLabel>
      <Select
        labelId="demo-simple-select-label"
        id="demo-simple-select"
        value={author}
        onChange={handleChange}
        label="Author"
      >
        {renderAuthors()}
      </Select>
      <TextField
        label="Publication Date"
        variant="standard"
        type="date"
        value={publicationDate}
        onChange={(e) => {
          setPublicationDate(e.target.value);
        }}
      />
      <Button variant="contained" onClick={handleClick}>
        Save
      </Button>
    </Box>
  );
}

BookForm.propTypes = {
  addBook: PropTypes.func,
  bookEdit: PropTypes.object,
  authorList: PropTypes.array,
};

export default BookForm;
