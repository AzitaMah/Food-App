#FROM node:alpine AS development
#
#ENV NODE_ENV development
#
#WORKDIR /react-app
#
#COPY ./package*.json /react-app
#
#RUN npm install
#
#COPY . .
#
## Starting our application
#CMD ["npm","start"]

FROM node:16.17.1-alpine3.16 as build
WORKDIR /react-app
COPY . /react-app
RUN npm ci
RUN npm run build

FROM nginx:1.23.1-alpine
EXPOSE 80
COPY ./docker/nginx/conf.d/default.conf /etc/nginx/conf.d/default.conf
COPY --from=build /react-app/build /usr/share/nginx/html
HEALTHCHECK CMD curl --fail http://localhost:3000 || exit 1
CMD ["nginx", "-g", "daemon off;"]
