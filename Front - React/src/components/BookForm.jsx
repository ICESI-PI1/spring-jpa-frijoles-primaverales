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

  useEffect(() => {
    if (bookEdit) {
      setId(bookEdit.id || "");
      setTitle(bookEdit.title || "");
      setAuthor(bookEdit.author || "");
    } else {
      setId("");
      setTitle("");
      setAuthor("");
    }
  }, [bookEdit]);
  const handleClick = () => {
    addBook({ id, title, author });
  };

  const handleChange = (event) => {
    setAuthor(event.target.value);
  };

  const renderAuthors = () => {
    return authorList.map((author) => (
      <MenuItem key={author.id} value={author}>{author.name}</MenuItem>
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
      <Select
        labelId="demo-simple-select-label"
        id="demo-simple-select"
        value={author}
        label="Author"
        onChange={handleChange}
      >
        {renderAuthors()}
      </Select>
      <Button variant="contained" onClick={handleClick}>
        Save
      </Button>
    </Box>
  );
}

BookForm.propTypes = {
  addBook: PropTypes.func,
  bookEdit: PropTypes.object,
};

export default BookForm;
