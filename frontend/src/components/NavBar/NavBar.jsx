import { styled, alpha } from "@mui/material/styles";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import InputBase from "@mui/material/InputBase";
import MenuItem from "@mui/material/MenuItem";
import Menu from "@mui/material/Menu";
import SearchIcon from "@mui/icons-material/Search";
import { Select } from "@mui/material/";

import MoreIcon from "@mui/icons-material/MoreVert";
import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Logo from "../../assets/images/logo_football_turf.png"
import TurfService from "../../services/TurfService";

const Search = styled("div")(({ theme }) => ({
  position: "relative",
  borderRadius: theme.shape.borderRadius,
  backgroundColor: alpha(theme.palette.common.white, 0.15),
  "&:hover": {
    backgroundColor: alpha(theme.palette.common.white, 0.25),
  },
  marginRight: theme.spacing(2),
  marginLeft: 0,
  width: "100%",
  [theme.breakpoints.up("sm")]: {
    marginLeft: theme.spacing(3),
    width: "auto",
  },
}));

const SearchIconWrapper = styled("div")(({ theme }) => ({
  padding: theme.spacing(0, 2),
  height: "100%",
  position: "absolute",
  pointerEvents: "none",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
}));

const StyledInputBase = styled(InputBase)(({ theme }) => ({
  color: "inherit",
  "& .MuiInputBase-input": {
    padding: theme.spacing(1, 1, 1, 0),
    // vertical padding + font size from searchIcon
    paddingLeft: `calc(1em + ${theme.spacing(4)})`,
    transition: theme.transitions.create("width"),
    width: "100%",
    [theme.breakpoints.up("sm")]: {
      width: "20ch",
      "&:focus": {
        width: "60ch",
      },
    },
  },
}));

const NavBar = ({ type, setType, setTurfs, position, searchText, setSearchText }) => {

  const [anchorEl, setAnchorEl] = useState(null);
  const [mobileMoreAnchorEl, setMobileMoreAnchorEl] = useState(null);

  const isMenuOpen = Boolean(anchorEl);
  const isMobileMenuOpen = Boolean(mobileMoreAnchorEl);

  const [account, setAccount] = useState({});
  useEffect(() => {
    setAccount(window.localStorage.getItem("account"));
  }, [])

  const [url, setUrl] = useState("");
  var tmpUrl = document.URL;
  useEffect(() => {
    setUrl(tmpUrl);
  }, [tmpUrl])

  const handleLogout = () => {
    window.localStorage.removeItem('account');
    setAccount(null)
  }

  const handleMobileMenuClose = () => {
    setMobileMoreAnchorEl(null);
  };

  const handleMenuClose = () => {
    setAnchorEl(null);
    handleMobileMenuClose();
  };

  const handleMobileMenuOpen = (event) => {
    setMobileMoreAnchorEl(event.currentTarget);
  };

  const menuId = "primary-search-account-menu";
  const renderMenu = (
    <Menu
      anchorEl={anchorEl}
      anchorOrigin={{
        vertical: "top",
        horizontal: "right",
      }}
      id={menuId}
      keepMounted
      transformOrigin={{
        vertical: "top",
        horizontal: "right",
      }}
      open={isMenuOpen}
      onClose={handleMenuClose}
    >
      <MenuItem onClick={handleMenuClose}>Profile</MenuItem>
      <MenuItem onClick={handleMenuClose}>My account</MenuItem>
    </Menu>
  );

  const mobileMenuId = "primary-search-account-menu-mobile";
  const renderMobileMenu = (
    <Menu
      anchorEl={mobileMoreAnchorEl}
      anchorOrigin={{
        vertical: "top",
        horizontal: "right",
      }}
      id={mobileMenuId}
      keepMounted
      transformOrigin={{
        vertical: "top",
        horizontal: "right",
      }}
      open={isMobileMenuOpen}
      onClose={handleMobileMenuClose}
    >
      <Link to="/login" className="py-5 px-10 font-medium block">
        Login
      </Link>

    </Menu>
  );
  const navigate = useNavigate();
  const handleClickLogo = () => {
    navigate("/");
  }
  const handleChangeType = (event) => {
    const type = event.target.value;
    const searchDto = {
      name: searchText,
      turfType: type === 3 ? null : type,
      longitude: `${position?.coords?.longitude}`,
      latitude: `${position?.coords?.latitude}`,
    }

    TurfService.searchMainTurf(searchDto).then((response) => {
      const { data } = response;
      setTurfs(data)
    });

    setType(type)
  }

  const handleChangeSearch = (event) => {
    var tmpSearch = event.target.value
    const searchDto = {
      name: tmpSearch,
      turfType: type === 3 ? null : type,
      longitude: `${position?.coords?.longitude}`,
      latitude: `${position?.coords?.latitude}`,
    }

    TurfService.searchMainTurf(searchDto).then((response) => {
      const { data } = response;
      setTurfs(data)
    });
    setSearchText(tmpSearch)
  }
  return (
    <div id="back-to-top-anchor">
      <Box sx={{ flexGrow: 1 }}>
        <AppBar position="static">
          <Toolbar>
            <IconButton
              size="large"
              edge="start"
              color="inherit"
              aria-label="open drawer"
              sx={{ mr: 2 }}
              onClick={handleClickLogo}
            >
              <img src={Logo} className="w-12" />
            </IconButton>
            <Typography
              variant="h6"
              noWrap
              component="div"
              sx={{ display: { xs: "none", sm: "block" } }}
            >
              FootballTurf
            </Typography>
            {url.includes("turf") ? null : (
              <div className = "flex justify-center items-center">
                <Search
                  onChange={handleChangeSearch}>
                  <SearchIconWrapper>
                    <SearchIcon />
                  </SearchIconWrapper>
                  <StyledInputBase
                    placeholder="Search…"
                    inputProps={{ "aria-label": "search" }}
                  />
                </Search>

                <Select
                  className = "h-10 border-1"
                  labelId="demo-select-small"
                  id="demo-select-small"
                  value={type}
                  onChange={handleChangeType}
                >
                  <MenuItem value={3}>Tất cả</MenuItem>
                  <MenuItem value={0}>Sân 5</MenuItem>
                  <MenuItem value={1}>Sân 7</MenuItem>
                </Select>
              </div>
            )}


            <Box sx={{ flexGrow: 1 }} />
            <Box sx={{ display: { xs: "none", md: "flex" } }}>
              {!account ? (<Link to="/login" className="px-3 ">
                <button className="text-lg">Đăng nhập</button>
              </Link>) : <button className="text-lg" onClick={handleLogout}>Đăng xuất</button>}
            </Box>
            <Box sx={{ display: { xs: "flex", md: "none" } }}>
              <IconButton
                size="large"
                aria-label="show more"
                aria-controls={mobileMenuId}
                aria-haspopup="true"
                onClick={handleMobileMenuOpen}
                color="inherit"
              >
                <MoreIcon />
              </IconButton>
            </Box>
          </Toolbar>
        </AppBar>
        {renderMobileMenu}
        {renderMenu}
      </Box>
    </div>
  );
};

export default NavBar;
