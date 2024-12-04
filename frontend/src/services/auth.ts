import axios from "axios";

export const login = async (username: string, password: string) => {
  const formData = new FormData();
  formData.append("username", username);
  formData.append("password", password);

  return axios.post("http://localhost:8080/login", formData, {
    withCredentials: true,
  });
};
