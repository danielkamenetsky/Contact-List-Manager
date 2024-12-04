// App.tsx
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Login } from "./components/Login";
import { ContactsList } from "./components/ContactsList";
import { AuthProvider } from "./context/AuthContext"; // Change to AuthProvider

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/contacts" element={<ContactsList />} />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
