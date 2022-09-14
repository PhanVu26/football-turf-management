## DEV
FROM node:16
RUN mkdir -p /app
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build
EXPOSE 4200
ENV PORT=4200
ENV REACT_APP_BASE_URL=http://localhost:8080/api
CMD ["npm", "start"]


## PRO
# FROM node:16
# RUN npm install -g serve
# COPY ["package.json", "package-lock.json*", "./"]
# RUN npm install 
# COPY . .
# CMD ["npm", "run", "build"]
# CMD serve -p $PORT -s dist

