from flask import Flask, render_template

app = Flask(__name__)


@app.route('/')
def init():                            # this is a comment. You can create your own function name
    return '<h1>Welcome to Flask! </h1>'


if __name__ == '__main__':
    app.run(host='0.0.0.0',debug=True)   # host='0.0.0.0' means whatever your public ip assigned will be used
