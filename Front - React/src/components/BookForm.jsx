import { useEffect, useState } from "react";
import PropTypes from "prop-types";
import { Box, Button, TextField } from "@mui/material";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";

function BookForm({ authorList, addBook, bookEdit, setBookEdit }) {
  const [id, setId] = useState("");
  const [title, setTitle] = useState("");
  const [author, setAuthor] = useState("");
  const [authorId, setAuthorId] = useState("");
  const [publicationDate, setPublicationDate] = useState("");

  useEffect(() => {
    if (bookEdit) {
      setId(bookEdit.id || "");
      setTitle(bookEdit.title || "");
      setAuthor(bookEdit.author || "");
      setAuthorId(bookEdit.author.id || "");

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
      setAuthorId("");
    }
  }, [bookEdit]);

  const handleClick = () => {
    if (!title || !authorId || !publicationDate) {
      alert("Todos los campos son obligatorios. Por favor, completa la información.");
      return;
    }

  
    const currentDate = new Date().toISOString().split('T')[0];
    if (publicationDate > currentDate) {
      alert("La fecha de publicación no puede ser mayor que la fecha actual.");
      setId("");
      setTitle("");
      setAuthor("");
      setPublicationDate("");
      setAuthorId("");
      return; 
    }
  
    addBook({ id, title, author, publicationDate });
  };

  const handleClear = () => {
    setId("");
    setTitle("");
    setAuthor("");
    setPublicationDate("");
    setAuthorId("");
    setBookEdit(null);
  };

  const handleChange = (event) => {
    const selectedAuthorId = event.target.value;
    setAuthorId(selectedAuthorId);
    const selectedAuthor = authorList.find(author => author.id === selectedAuthorId);
    setAuthor(selectedAuthor);
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
        value={authorId}
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
      <Button variant="contained" onClick={handleClear}
      sx={{ backgroundColor: '#FFD700', color: '#2E2E2E', '&:hover': {
        backgroundColor: '#FFA500',
        color: "#FFFFFF"
      },}}
      >
        Clear
      </Button>
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
  setBookEdit: PropTypes.func,
};

export default BookForm;
