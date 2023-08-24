<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<div class="wrap start-page">
    <h1><i class="home-icon"></i>早上好，管理员**</h1>
    <dl>
        <dt>系统时间</dt>
    </dl>
    <dl>
        <dt>首页推荐信息</dt>
    </dl>

    <dl>
        <dt>统计</dt>
        <dd>
            <ul>
                <li>
                    <span>总访问量：</span>
                    <span>1356</span>
                </li>
                <li>
                    <span>用户量：</span>
                    <span>${accountCount}</span>
                </li>
                <li>
                    <span>总评论数：</span>
                    <span>${commentCount}</span>
                </li>
            </ul>
        </dd>
    </dl>

    <dl>
        <dt>系统信息</dt>
        <dd>
            <ul>
                <li>
                    <span>服务器操作系统：</span>
                    <span>${systemVersion}</span>
                </li>
                <li>
                    <span>WEB服务器：</span>
                    <span>APACHE2.2.9</span>
                </li>
                <li>
                    <span>MySQL 版本：</span>
                    <span>5.5.20</span>
                </li>
            </ul>
        </dd>
    </dl>
    <dl>
        <dt>最新推荐留言</dt>
    </dl>
</div>