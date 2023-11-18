import PropTypes from 'prop-types'
import { TableRow, TableCell, Button, } from '@mui/material'
import { Link } from 'react-router-dom'

function AuthorRow({author, delAuthor, editAuthor }) {
  const  handleDelete = () =>  {
    delAuthor(author.id)
  }
  return (
    <TableRow
          key={author.id}
          sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
        >
          <TableCell align="right">{author.id}</TableCell>
          <TableCell align="left">{author.name}</TableCell>
          <TableCell align="left">{author.nationality}</TableCell>
          <TableCell align="left">
            <Button variant="contained" color="error" onClick={handleDelete}>Delete</Button>&nbsp;
            <Button variant="contained" color="success" onClick={()=>{editAuthor(author)}}>Edit</Button>&nbsp;
            <Link to={`/authors/${author.id}/books`}>
            <Button variant="contained" color="info">Show Books</Button>
            </Link>
          </TableCell>
        </TableRow>  )
}

AuthorRow.propTypes = {
    author: PropTypes.object,
    delAuthor: PropTypes.func,
    editAuthor: PropTypes.func
}

export default AuthorRow